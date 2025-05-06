package com.example.safersapp

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.android.gms.location.*

class LocationManager(private val context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback? = null
    private val handler = Handler(Looper.getMainLooper())

    /** 🔵 10분마다 현재 위치 전송 */
    fun startPeriodicLocationUpdates() {
        handler.post(object : Runnable {
            override fun run() {
                getCurrentLocationOnce()
                handler.postDelayed(this, 10 * 60 * 1000L)  // 10분 후 반복
            }
        })
    }

    /** 🔵 현재 위치 한 번 전송 */
    private fun getCurrentLocationOnce() {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    ApiClient.sendLocationToServer(location.latitude, location.longitude)
                }
            }
    }

    /** 🔵 서버에 실시간 GPS 요청 확인 (1분마다 폴링) */
    fun startCheckRealtimeGpsPolling() {
        handler.post(object : Runnable {
            override fun run() {
                checkRealtimeGpsFromServer()
                handler.postDelayed(this, 60 * 1000L)  // 1분마다 확인
            }
        })
    }

    /** 🔵 서버로 실시간 GPS 요청 여부 확인 */
    private fun checkRealtimeGpsFromServer() {
        ApiClient.checkRealtimeGps { isRealtimeRequested ->
            if (isRealtimeRequested) {
                startRealtimeLocationUpdates()
            }
        }
    }

    /** 🔵 실시간 위치 업데이트 시작 */
    private fun startRealtimeLocationUpdates() {
        if (locationCallback != null) return  // 이미 실행중이면 무시

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                for (location in result.locations) {
                    ApiClient.sendLocationToServer(location.latitude, location.longitude)
                }
            }
        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L).build()
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!, Looper.getMainLooper())

        // ✅ 1분 후 실시간 종료 + 다시 periodic 유지
        handler.postDelayed({
            stopRealtimeLocationUpdates()
        }, 60 * 1000L)  // 실시간 GPS 1분 유지
    }

    /** 🔵 실시간 업데이트 중지 */
    private fun stopRealtimeLocationUpdates() {
        if (locationCallback != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback!!)
            locationCallback = null
            println("실시간 GPS 중지됨")
        }
    }

    /** 🔵 리소스 정리 */
    fun cleanup() {
        stopRealtimeLocationUpdates()
        handler.removeCallbacksAndMessages(null)
    }
}
