package com.example.wearsafers

import android.util.Log
import okhttp3.*
import java.io.IOException

object ApiClient {
    private val client = OkHttpClient()

    fun sendHeartRateToServer(heartRate: Float) {
        val requestBody = FormBody.Builder()
            .add("heartRate", heartRate.toString())
            .build()

        val request = Request.Builder()
            .url("https://yourserver.com/api/heartrate")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "Heart rate send failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("ApiClient", "Heart rate sent successfully")
                } else {
                    Log.e("ApiClient", "Heart rate server error: ${response.code}")
                }
            }
        })
    }

    fun sendAccelerationToServer(acceleration: Float) {
        val requestBody = FormBody.Builder()
            .add("acceleration", acceleration.toString())
            .build()

        val request = Request.Builder()
            .url("https://yourserver.com/api/acceleration")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "Acceleration send failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("ApiClient", "Acceleration sent successfully")
                } else {
                    Log.e("ApiClient", "Acceleration server error: ${response.code}")
                }
            }
        })
    }

    fun sendStepCountToServer(stepCount: Int) {
        val requestBody = FormBody.Builder()
            .add("stepCount", stepCount.toString())
            .build()

        val request = Request.Builder()
            .url("https://yourserver.com/api/stepcount")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "Step count send failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("ApiClient", "Step count sent successfully")
                } else {
                    Log.e("ApiClient", "Step count server error: ${response.code}")
                }
            }
        })
    }

    fun sendLocationToServer(lat: Double, lon: Double) {
        val requestBody = FormBody.Builder()
            .add("latitude", lat.toString())
            .add("longitude", lon.toString())
            .build()

        val request = Request.Builder()
            .url("https://yourserver.com/api/location")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ApiClient", "Location send failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("ApiClient", "Location sent successfully")
                } else {
                    Log.e("ApiClient", "Location server error: ${response.code}")
                }
            }
        })
    }
}
