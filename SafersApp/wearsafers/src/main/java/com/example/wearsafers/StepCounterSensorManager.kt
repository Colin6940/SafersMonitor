private var stepCount = 0

override fun onSensorChanged(event: SensorEvent?) {
    when (event?.sensor?.type) {
        Sensor.TYPE_STEP_COUNTER -> {
            stepCount = event.values[0].toInt()
            ApiClient.sendStepCountToServer(stepCount)
        }
    }
}
