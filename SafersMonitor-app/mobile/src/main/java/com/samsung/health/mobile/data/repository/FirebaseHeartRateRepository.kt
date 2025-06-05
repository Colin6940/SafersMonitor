package com.samsung.health.mobile.data.repository

import com.google.firebase.database.*
import com.samsung.health.mobile.data.model.HeartRateData
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class FirebaseHeartRateRepository @Inject constructor() {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val heartRateRef: DatabaseReference = database.getReference("heart_rates")
    private var dataCollectionJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    suspend fun saveHeartRate(heartRateData: HeartRateData): Result<Unit> {
        return try {
            // 각 사용자별로 데이터를 구분하여 저장
            val userHeartRatesRef = heartRateRef
                .child(heartRateData.userId)
                .child(heartRateData.timestamp.toString())
            
            suspendCoroutine { continuation ->
                userHeartRatesRef.setValue(heartRateData)
                    .addOnSuccessListener {
                        continuation.resume(Result.success(Unit))
                    }
                    .addOnFailureListener { exception ->
                        continuation.resume(Result.failure(exception))
                    }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun observeHeartRates(userId: String): Flow<List<HeartRateData>> = callbackFlow {
        val heartRatesListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val heartRates = snapshot.children.mapNotNull { 
                    it.getValue(HeartRateData::class.java) 
                }
                trySend(heartRates)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        val query = heartRateRef.child(userId).orderByChild("timestamp")
        query.addValueEventListener(heartRatesListener)

        awaitClose {
            query.removeEventListener(heartRatesListener)
        }
    }

    fun startPeriodicDataCollection(userId: String, onNewData: suspend (List<HeartRateData>) -> Unit) {
        stopPeriodicDataCollection() // 이전 작업이 있다면 중지

        dataCollectionJob = coroutineScope.launch {
            observeHeartRates(userId).collect { heartRates ->
                onNewData(heartRates)
            }
        }
    }

    fun stopPeriodicDataCollection() {
        dataCollectionJob?.cancel()
        dataCollectionJob = null
    }
}
