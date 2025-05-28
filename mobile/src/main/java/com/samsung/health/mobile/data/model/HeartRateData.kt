package com.samsung.health.mobile.data.model

// 서버로 전송할 데이터 모델
data class HeartRateData(
    val timestamp: Long = System.currentTimeMillis(),
    val hr: Int = 0,
    val userId: String = "" // 사용자 식별을 위한 ID
) {
    // Firebase는 매개변수가 없는 생성자가 필요합니다
    constructor() : this(0L, 0, "")
}

