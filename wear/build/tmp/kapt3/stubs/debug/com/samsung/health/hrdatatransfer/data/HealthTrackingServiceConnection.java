package com.samsung.health.hrdatatransfer.data;

import android.content.Context;
import android.util.Log;
import com.samsung.android.service.health.tracking.ConnectionListener;
import com.samsung.android.service.health.tracking.HealthTrackerException;
import com.samsung.android.service.health.tracking.HealthTrackingService;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;", "", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "connected", "", "connectionFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "getConnectionFlow", "()Lkotlinx/coroutines/flow/Flow;", "healthTrackingService", "Lcom/samsung/android/service/health/tracking/HealthTrackingService;", "disconnect", "", "getHealthTrackingService", "wear_debug"})
@kotlinx.coroutines.ExperimentalCoroutinesApi()
public final class HealthTrackingServiceConnection {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private boolean connected = false;
    @org.jetbrains.annotations.Nullable()
    private com.samsung.android.service.health.tracking.HealthTrackingService healthTrackingService;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.samsung.health.hrdatatransfer.data.ConnectionMessage> connectionFlow = null;
    
    @javax.inject.Inject()
    public HealthTrackingServiceConnection(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.samsung.health.hrdatatransfer.data.ConnectionMessage> getConnectionFlow() {
        return null;
    }
    
    private final void disconnect() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.samsung.android.service.health.tracking.HealthTrackingService getHealthTrackingService() {
        return null;
    }
}