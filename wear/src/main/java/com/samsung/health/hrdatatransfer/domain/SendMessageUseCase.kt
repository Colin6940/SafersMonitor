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

package com.samsung.health.hrdatatransfer.domain

import android.util.Log
import com.samsung.health.data.TrackedData
import com.samsung.health.hrdatatransfer.data.MessageRepository
import com.samsung.health.hrdatatransfer.data.TrackingRepository
import kotlinx.coroutines.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val TAG = "SendMessageUseCase"

private const val MESSAGE_PATH = "/msg"

class SendMessageUseCase @Inject constructor(
    private val messageRepository: MessageRepository,
    private val trackingRepository: TrackingRepository,
    private val getCapableNodes: GetCapableNodes
) {
    private var periodicSendingJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    suspend operator fun invoke(periodic: Boolean = false): Boolean {
        return if (periodic) {
            startPeriodicSending()
            true
        } else {
            sendOnce()
        }
    }

    private suspend fun sendOnce(): Boolean {
        val nodes = getCapableNodes()
        return if (nodes.isNotEmpty()) {
            val node = nodes.first()
            val message = encodeMessage(trackingRepository.getValidHrData())
            messageRepository.sendMessage(message, node, MESSAGE_PATH)
            Log.i(TAG, "Data sent successfully")
            true
        } else {
            Log.i(TAG, "No nodes available")
            false
        }
    }

    private fun startPeriodicSending() {
        stopPeriodicSending()
        
        periodicSendingJob = coroutineScope.launch {
            while (isActive) {
                try {
                    if (sendOnce()) {
                        Log.i(TAG, "Periodic data sent successfully")
                    } else {
                        Log.e(TAG, "Failed to send periodic data")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error sending periodic data", e)
                }
                delay(10000) // 10초 대기
            }
        }
    }

    fun stopPeriodicSending() {
        periodicSendingJob?.cancel()
        periodicSendingJob = null
        Log.i(TAG, "Periodic sending stopped")
    }

    fun encodeMessage(trackedData: ArrayList<TrackedData>): String {
        return Json.encodeToString(trackedData)
    }
}