package com.example.safersapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class AccelerometerSensorManager(private val context: Context) : SensorEventListener {

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var accelerometerSensor: Sensor? = null

    private var shockThreshold = 20.0  // 기본 민감도
    var shockDetectedCallback: (() -> Unit)? = null  // UI 표시 콜백

    init {
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    fun setShockThreshold(threshold: Double) {
        shockThreshold = threshold
    }

    fun start() {
        accelerometerSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val ax = event.values[0]
            val ay = event.values[1]
            val az = event.values[2]
            val acceleration = Math.sqrt((ax * ax + ay * ay + az * az).toDouble())

            ApiClient.sendAccelerationToServer(acceleration.toFloat())

            if (acceleration >= shockThreshold) {
                ApiClient.sendShockDetectedToServer(acceleration.toFloat())
                shockDetectedCallback?.invoke()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // 필요 시 처리
    }
}
