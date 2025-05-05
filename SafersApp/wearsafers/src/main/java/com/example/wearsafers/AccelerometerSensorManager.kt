private var acceleration = 0.0

override fun onSensorChanged(event: SensorEvent?) {
    when (event?.sensor?.type) {
        Sensor.TYPE_HEART_RATE -> {
            val heartRate = event.values[0]
            ApiClient.sendHeartRateToServer(heartRate)
        }
        Sensor.TYPE_ACCELEROMETER -> {
            val ax = event.values[0]
            val ay = event.values[1]
            val az = event.values[2]
            acceleration = Math.sqrt((ax*ax + ay*ay + az*az).toDouble())

            ApiClient.sendAccelerationToServer(acceleration.toFloat())
        }
    }
}
