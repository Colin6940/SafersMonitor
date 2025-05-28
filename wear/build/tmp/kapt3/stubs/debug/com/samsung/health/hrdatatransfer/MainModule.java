package com.samsung.health.hrdatatransfer;

import android.content.Context;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Wearable;
import com.samsung.health.hrdatatransfer.data.CapabilityRepository;
import com.samsung.health.hrdatatransfer.data.CapabilityRepositoryImpl;
import com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection;
import com.samsung.health.hrdatatransfer.data.MessageRepository;
import com.samsung.health.hrdatatransfer.data.MessageRepositoryImpl;
import com.samsung.health.hrdatatransfer.data.TrackingRepository;
import com.samsung.health.hrdatatransfer.data.TrackingRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0007J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007\u00a8\u0006\u0016"}, d2 = {"Lcom/samsung/health/hrdatatransfer/MainModule;", "", "()V", "provideApplicationCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "provideCapabilitiesRepository", "Lcom/samsung/health/hrdatatransfer/data/CapabilityRepository;", "capabilityClient", "Lcom/google/android/gms/wearable/CapabilityClient;", "provideCapabilityClient", "context", "Landroid/content/Context;", "provideMessageClient", "Lcom/google/android/gms/wearable/MessageClient;", "provideMessageRepository", "Lcom/samsung/health/hrdatatransfer/data/MessageRepository;", "messageClient", "provideTrackingRepository", "Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;", "coroutineScope", "healthTrackingServiceConnection", "Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;", "wear_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class MainModule {
    
    public MainModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope provideApplicationCoroutineScope() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.wearable.CapabilityClient provideCapabilityClient(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.wearable.MessageClient provideMessageClient(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.data.TrackingRepository provideTrackingRepository(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection healthTrackingServiceConnection, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.data.MessageRepository provideMessageRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.MessageClient messageClient) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.data.CapabilityRepository provideCapabilitiesRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.CapabilityClient capabilityClient) {
        return null;
    }
}