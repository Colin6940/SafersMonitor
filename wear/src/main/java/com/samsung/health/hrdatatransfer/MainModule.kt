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

package com.samsung.health.hrdatatransfer

import android.content.Context
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import com.samsung.health.hrdatatransfer.data.CapabilityRepository
import com.samsung.health.hrdatatransfer.data.CapabilityRepositoryImpl
import com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection
import com.samsung.health.hrdatatransfer.data.MessageRepository
import com.samsung.health.hrdatatransfer.data.MessageRepositoryImpl
import com.samsung.health.hrdatatransfer.data.TrackingRepository
import com.samsung.health.hrdatatransfer.data.TrackingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideApplicationCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Main)
    }

    @Provides
    @Singleton
    fun provideCapabilityClient(@ApplicationContext context: Context): CapabilityClient {
        return Wearable.getCapabilityClient(context)
    }

    @Provides
    @Singleton
    fun provideMessageClient(@ApplicationContext context: Context): MessageClient {
        return Wearable.getMessageClient(context)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    @Singleton
    fun provideTrackingRepository(
        coroutineScope: CoroutineScope,
        healthTrackingServiceConnection: HealthTrackingServiceConnection,
        @ApplicationContext context: Context
    ): TrackingRepository {
        return TrackingRepositoryImpl(coroutineScope, healthTrackingServiceConnection, context)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(messageClient: MessageClient): MessageRepository {
        return MessageRepositoryImpl(messageClient)
    }

    @Provides
    @Singleton
    fun provideCapabilitiesRepository(capabilityClient: CapabilityClient): CapabilityRepository {
        return CapabilityRepositoryImpl(capabilityClient)
    }
}