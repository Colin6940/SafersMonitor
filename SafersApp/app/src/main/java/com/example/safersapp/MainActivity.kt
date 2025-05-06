package com.example.safersapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.samsung.android.sdk.healthdata.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var dataStore: HealthDataStore
    private val client = OkHttpClient()

    companion object {
        private const val SERVER_URL = "http://192.168.75.76:8000/api/health_record"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1️⃣ 먼저 auth_token 확인
        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val authToken = sharedPref.getString("auth_token", null)

        if (authToken.isNullOrEmpty()) {
            // 로그인 안 되어 있으면 LoginActivity로 이동
            Toast.makeText(this, "로그인이 필요합니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            return
        }

        setContentView(R.layout.activity_main)

        // 2️⃣ Samsung Health 연결
        dataStore = HealthDataStore(this, object : HealthDataStore.ConnectionListener {
            override fun onConnected() {
                Log.d("MainActivity", "Samsung Health 연결 성공")
                Toast.makeText(this@MainActivity, "Samsung Health 연결 성공", Toast.LENGTH_SHORT).show()
                readHealthData(authToken)
            }

            override fun onConnectionFailed(result: HealthConnectionErrorResult?) {
                Log.e("MainActivity", "Samsung Health 연결 실패: ${result?.errorCode}")
                Toast.makeText(this@MainActivity, "Samsung Health 연결 실패: ${result?.errorCode}", Toast.LENGTH_SHORT).show()
            }

            override fun onDisconnected() {
                Log.e("MainActivity", "Samsung Health 연결 해제됨")
                Toast.makeText(this@MainActivity, "Samsung Health 연결 해제됨", Toast.LENGTH_SHORT).show()
            }
        })
        dataStore.connectService()
    }

    private fun readHealthData(authToken: String) {
        val resolver = HealthDataResolver(dataStore, null)

        val bpRequest = ReadRequest.Builder()
            .setDataType(HealthConstants.BloodPressure.HEALTH_DATA_TYPE)
            .build()

        resolver.read(bpRequest).setResultListener { result ->
            for (data in result) {
                try {
                    val systolic = data.getFloat(HealthConstants.BloodPressure.SYSTOLIC)
                    val diastolic = data.getFloat(HealthConstants.BloodPressure.DIASTOLIC)
                    Log.d("MainActivity", "혈압: $systolic / $diastolic")
                    sendToServer(authToken, systolic, diastolic, null, null)
                } catch (e: Exception) {
                    Log.e("MainActivity", "혈압 데이터 파싱 에러: ${e.message}")
                }
            }
        }

        val ecgRequest = ReadRequest.Builder()
            .setDataType(HealthConstants.Ecg.HEALTH_DATA_TYPE)
            .build()

        resolver.read(ecgRequest).setResultListener { result ->
            for (data in result) {
                try {
                    val ecgResult = data.getString(HealthConstants.Ecg.ECG_RESULT)
                    Log.d("MainActivity", "심전도: $ecgResult")
                    sendToServer(authToken, null, null, ecgResult, null)
                } catch (e: Exception) {
                    Log.e("MainActivity", "심전도 데이터 파싱 에러: ${e.message}")
                }
            }
        }

        val spo2Request = ReadRequest.Builder()
            .setDataType(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE)
            .build()

        resolver.read(spo2Request).setResultListener { result ->
            for (data in result) {
                try {
                    val spo2 = data.getFloat(HealthConstants.OxygenSaturation.SPO2)
                    Log.d("MainActivity", "SpO₂: $spo2")
                    sendToServer(authToken, null, null, null, spo2)
                } catch (e: Exception) {
                    Log.e("MainActivity", "산소포화도 데이터 파싱 에러: ${e.message}")
                }
            }
        }
    }

    private fun sendToServer(
        authToken: String,
        systolic: Float?, diastolic: Float?,
        ecgResult: String?, spo2: Float?
    ) {
        val jsonObject = JSONObject().apply {
            systolic?.let { put("systolic", it) }
            diastolic?.let { put("diastolic", it) }
            ecgResult?.let { put("ecg_result", it) }
            spo2?.let { put("spo2", it) }
        }

        val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(SERVER_URL)
            .addHeader("Authorization", "Bearer $authToken")  // 3️⃣ auth_token 추가
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("MainActivity", "서버 전송 실패: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("MainActivity", "서버 응답: ${response.code}")
                response.close()
            }
        })
    }
}
