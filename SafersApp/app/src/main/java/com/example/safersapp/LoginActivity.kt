package com.example.wearsafers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private lateinit var phoneInput: EditText
    private lateinit var loginButton: Button

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        phoneInput = findViewById(R.id.phoneInput)
        loginButton = findViewById(R.id.loginButton)

        // 전화번호 EditText 수정 못하게 설정
        phoneInput.isEnabled = false
        phoneInput.isFocusable = false

        // 권한 체크
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            getPhoneNumber()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE),
                PERMISSION_REQUEST_CODE)
        }

        loginButton.setOnClickListener {
            val phoneNumber = phoneInput.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "전화번호를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                login(phoneNumber)
            }
        }
    }

    private fun getPhoneNumber() {
        try {
            val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val phoneNumber = telephonyManager.line1Number
            if (phoneNumber != null && phoneNumber.isNotEmpty()) {
                phoneInput.setText(phoneNumber)
            } else {
                phoneInput.setText("불러오기 실패")
                Toast.makeText(this, "전화번호를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("LoginActivity", "전화번호 가져오기 실패: ${e.message}")
            phoneInput.setText("불러오기 실패")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.any { it == PackageManager.PERMISSION_GRANTED }) {
                getPhoneNumber()
            } else {
                Toast.makeText(this, "전화번호 권한 거부됨", Toast.LENGTH_SHORT).show()
                phoneInput.setText("권한 거부됨")
            }
        }
    }

    private fun login(phoneNumber: String) {
        val json = JSONObject().apply {
            put("username", phoneNumber) // 서버에 전화번호 전송
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://yourserver.com/api/login") // 서버 주소 수정
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "로그인 실패: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e("LoginActivity", "로그인 실패", e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (response.isSuccessful && body != null) {
                    try {
                        val jsonResponse = JSONObject(body)
                        val token = jsonResponse.getString("token")

                        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                        sharedPref.edit().putString("auth_token", token).apply()

                        Log.d("LoginActivity", "로그인 성공, 토큰: $token")

                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        Log.e("LoginActivity", "응답 파싱 에러", e)
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "서버 응답 에러", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Log.e("LoginActivity", "로그인 실패: HTTP ${response.code}")
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "로그인 실패: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
