package com.safersmonitor.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.safersmonitor.R

class QRLoginActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView
    private var isProcessing = false
    private var loginRequestId: String? = null
    private var listenerRegistration: ListenerRegistration? = null
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_login)

        barcodeView = findViewById(R.id.barcodeView)

        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                if (result != null && !isProcessing) {
                    isProcessing = true

                    val phoneNumber = result.text.trim()
                    Toast.makeText(this@QRLoginActivity, "QR 인식: $phoneNumber", Toast.LENGTH_SHORT).show()
                    barcodeView.pause()

                    // Firestore에 로그인 요청 등록
                    val loginRequest = hashMapOf(
                        "phone_number" to phoneNumber,
                        "status" to "pending",
                        "timestamp" to com.google.firebase.Timestamp.now()
                    )

                    // 새 document 생성
                    db.collection("qr_login_requests")
                        .add(loginRequest)
                        .addOnSuccessListener { docRef ->
                            loginRequestId = docRef.id
                            listenLoginResult(docRef.id)
                            showWaitingDialog()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this@QRLoginActivity, "파이어베이스 등록 실패", Toast.LENGTH_SHORT).show()
                            isProcessing = false
                            barcodeView.resume()
                        }
                }
            }

            override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {}
        })
    }

    // Firestore에서 해당 요청의 상태 변화를 실시간 감지
    private fun listenLoginResult(documentId: String) {
        listenerRegistration = db.collection("qr_login_requests")
            .document(documentId)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Toast.makeText(this, "파이어베이스 감시 오류", Toast.LENGTH_SHORT).show()
                    finishLogin(false, null)
                    return@addSnapshotListener
                }
                if (snapshot != null && snapshot.exists()) {
                    val status = snapshot.getString("status")
                    if (status == "success") {
                        val token = snapshot.getString("token")
                        finishLogin(true, token)
                    } else if (status == "fail") {
                        finishLogin(false, null)
                    }
                    // status==pending이면 아무 일도 하지 않음 (계속 대기)
                }
            }
    }

    private fun finishLogin(success: Boolean, token: String?) {
        listenerRegistration?.remove()
        listenerRegistration = null
        if (success) {
            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            // 토큰 전달 등 필요 시
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("auth_token", token)
            startActivity(intent)
            finish()
        } else {
            AlertDialog.Builder(this)
                .setTitle("로그인 실패")
                .setMessage("해당 번호가 등록되어 있지 않거나 서버 인증에 실패했습니다.")
                .setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                    isProcessing = false
                    barcodeView.resume()
                }
                .show()
        }
    }

    private var waitingDialog: AlertDialog? = null
    private fun showWaitingDialog() {
        if (waitingDialog == null) {
            waitingDialog = AlertDialog.Builder(this)
                .setTitle("로그인 대기")
                .setMessage("서버에서 인증을 처리 중입니다...\n잠시만 기다려주세요.")
                .setCancelable(false)
                .create()
        }
        waitingDialog?.show()
    }

    private fun hideWaitingDialog() {
        waitingDialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
        isProcessing = false
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
        listenerRegistration?.remove()
        hideWaitingDialog()
    }
}
