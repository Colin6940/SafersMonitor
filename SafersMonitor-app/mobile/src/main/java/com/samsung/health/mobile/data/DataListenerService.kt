/*
 * Copyright 2023 Samsung Electronics Co., Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsung.health.mobile.data

import android.content.Intent
import android.util.Log
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.samsung.health.mobile.data.model.HeartRateData
import com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository
import com.samsung.health.mobile.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DataListenerService"
private const val MESSAGE_PATH = "/msg"

@AndroidEntryPoint
class DataListenerService : WearableListenerService() {

    @Inject
    lateinit var firebaseHeartRateRepository: FirebaseHeartRateRepository
    
    @Inject
    lateinit var userManager: UserManager

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)

        val value = messageEvent.data.decodeToString()
        Log.d(TAG, "Received message from watch: $value")
        
        when (messageEvent.path) {
            MESSAGE_PATH -> {
                if (value.isNotEmpty()) {
                    // MainActivity로 데이터 전달
                    startActivity(
                        Intent(this, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("message", value)
                    )
                    
                    // Firebase에 데이터 저장
                    serviceScope.launch {
                        try {
                            Log.d(TAG, "Parsing heart rate data from watch")
                            val heartRateDataList = parseHeartRateDataList(value)
                            val userId = userManager.getCurrentUserId()
                            Log.d(TAG, "Processing ${heartRateDataList.size} heart rate records")
                            
                            // 각 데이터를 Firebase에 즉시 저장
                            heartRateDataList.forEach { data ->
                                try {
                                    val dataToSave = data.copy(
                                        userId = userId,
                                        timestamp = System.currentTimeMillis()
                                    )
                                    Log.d(TAG, "Saving heart rate data: $dataToSave")
                                    
                                    firebaseHeartRateRepository.saveHeartRate(dataToSave)
                                        .onSuccess {
                                            Log.i(TAG, "Successfully saved heart rate: ${dataToSave.hr}")
                                        }
                                        .onFailure { error ->
                                            Log.e(TAG, "Failed to save heart rate data", error)
                                        }
                                } catch (e: Exception) {
                                    Log.e(TAG, "Error saving individual heart rate data", e)
                                }
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "Error processing heart rate data from watch", e)
                        }
                    }
                }
            }
            else -> {
                Log.w(TAG, "Received message with unknown path: ${messageEvent.path}")
            }
        }
    }

    private fun parseHeartRateDataList(jsonString: String): List<HeartRateData> {
        return try {
            val type = object : TypeToken<List<HeartRateData>>() {}.type
            Gson().fromJson(jsonString, type)
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing JSON data from watch: $jsonString", e)
            throw e
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.launch {
            Log.i(TAG, "DataListenerService destroyed")
        }
    }
}