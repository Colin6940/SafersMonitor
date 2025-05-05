package com.example.wearsafers

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.MessageClient

class HeartRateService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var heartRateSensor: Sensor? = null
    private val TAG = "HeartRateService"

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)

        if (heartRateSensor != null) {
            sensorManager.registerListener(this, heartRateSensor, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "Heart rate sensor registered")
        } else {
            Log.d(TAG, "No heart rate sensor found")
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_HEART_RATE) {
            val heartRate = event.values[0]
            Log.d(TAG, "Heart rate: $heartRate")
            ApiClient.sendHeartRateToServer(heartRate)
        }
    }

    private fun sendHeartRateToPhone(heartRate: Float) {
        val messageClient: MessageClient = Wearable.getMessageClient(this)
        Wearable.getNodeClient(this).connectedNodes.addOnSuccessListener { nodes ->
            for (node in nodes) {
                val message = heartRate.toString().toByteArray()
                messageClient.sendMessage(node.id, "/heartrate", message)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "Sent heart rate to phone: $heartRate")
                        } else {
                            Log.e(TAG, "Failed to send heart rate")
                        }
                    }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        Log.d(TAG, "Service destroyed, sensor unregistered")
    }
}
