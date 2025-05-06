package com.example.safersmanagerapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safersmanagerapp.R
import com.example.safersmanagerapp.network.QRLoginRequest
import com.example.safersmanagerapp.network.QRLoginResponse
import com.example.safersmanagerapp.network.RetrofitInstance
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QRLoginActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView
    private var isProcessing = false  // ✅ 중복 스캔 방지 플래그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_login)

        barcodeView = findViewById(R.id.barcodeView)

        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                if (result != null && !isProcessing) {
                    isProcessing = true  // ✅ 처리 중 표시

                    val phoneNumber = result.text
                    Toast.makeText(this@QRLoginActivity, "QR 인식: $phoneNumber", Toast.LENGTH_SHORT).show()

                    // ✅ 서버 요청
                    val call = RetrofitInstance.api.qrLogin(QRLoginRequest(username = phoneNumber))
                    call.enqueue(object : Callback<QRLoginResponse> {
                        override fun onResponse(call: Call<QRLoginResponse>, response: Response<QRLoginResponse>) {
                            if (response.isSuccessful && response.body() != null) {
                                val token = response.body()!!.token
                                Toast.makeText(this@QRLoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()

                                // Dashboard 이동
                                val intent = Intent(this@QRLoginActivity, DashboardActivity::class.java)
                                intent.putExtra("auth_token", token)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@QRLoginActivity, "로그인 실패: ${response.code()}", Toast.LENGTH_SHORT).show()
                                isProcessing = false  // 실패 시 다시 스캔 가능
                            }
                        }

                        override fun onFailure(call: Call<QRLoginResponse>, t: Throwable) {
                            Toast.makeText(this@QRLoginActivity, "서버 연결 실패: ${t.message}", Toast.LENGTH_SHORT).show()
                            isProcessing = false
                        }
                    })

                    // ✅ 필요시 스캔 멈추기
                    barcodeView.pause()
                }
            }

            override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {
                // (선택적 기능: 인식 포인트 시각화 필요 시 작성)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
        isProcessing = false  // ✅ 액티비티 돌아올 때 초기화
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
