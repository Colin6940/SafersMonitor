package com.example.safersapp

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

object ApiClient {

    private const val BASE_URL = "http://192.168.75.76:8000"
    private const val DEVICE_ID = "DEVICE_ID_고정값_또는_설정값"
    private val client = OkHttpClient()

    fun sendAccelerationToServer(acceleration: Float) {
        val json = JSONObject().apply {
            put("device_id", DEVICE_ID)
            put("acceleration", acceleration)
        }
        post("/api/acceleration", json)
    }

    fun sendStepCountToServer(stepCount: Int) {
        val json = JSONObject().apply {
            put("device_id", DEVICE_ID)
            put("step_count", stepCount)
        }
        post("/api/stepcount", json)
    }

    fun sendLocationToServer(latitude: Double, longitude: Double) {
        val json = JSONObject().apply {
            put("device_id", DEVICE_ID)
            put("latitude", latitude)
            put("longitude", longitude)
        }
        post("/api/location", json)
    }

    fun sendShockDetectedToServer(acceleration: Float) {  // ⚠️ 새 함수 추가!
        val json = JSONObject().apply {
            put("device_id", DEVICE_ID)
            put("shock_acceleration", acceleration)
        }
        post("/api/shock", json)
    }

    fun checkRealtimeGps(callback: (Boolean) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/api/request_realtime_gps?device_id=$DEVICE_ID")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "실시간 GPS 요청 실패: ${e.message}")
                callback(false)
            }

            override fun onResponse(call: Call, response: Response) {
                val isRealtime = response.body?.string()?.contains("true") ?: false
                callback(isRealtime)
            }
        })
    }

    private fun post(endpoint: String, json: JSONObject) {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = json.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(BASE_URL + endpoint)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "POST 실패: $endpoint, ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("ApiClient", "POST 성공: $endpoint → ${response.code}")
            }
        })
    }
}
