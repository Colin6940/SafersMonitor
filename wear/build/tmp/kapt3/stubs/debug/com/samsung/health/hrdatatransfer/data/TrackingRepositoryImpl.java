package com.samsung.health.hrdatatransfer.data;

import android.content.Context;
import android.util.Log;
import com.samsung.android.service.health.tracking.HealthTracker;
import com.samsung.android.service.health.tracking.HealthTrackingService;
import com.samsung.android.service.health.tracking.data.DataPoint;
import com.samsung.android.service.health.tracking.data.HealthTrackerType;
import com.samsung.android.service.health.tracking.data.ValueKey;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.R;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001fH\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\fH\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020$H\u0016J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0097@\u00a2\u0006\u0002\u0010+J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020$H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R6\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackingRepositoryImpl;", "Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "healthTrackingServiceConnection", "Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;", "context", "Landroid/content/Context;", "(Lkotlinx/coroutines/CoroutineScope;Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;Landroid/content/Context;)V", "errors", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getErrors", "()Ljava/util/HashMap;", "setErrors", "(Ljava/util/HashMap;)V", "healthTrackingService", "Lcom/samsung/android/service/health/tracking/HealthTrackingService;", "heartRateTracker", "Lcom/samsung/android/service/health/tracking/HealthTracker;", "listenerSet", "", "maxValuesToKeep", "trackingType", "Lcom/samsung/android/service/health/tracking/data/HealthTrackerType;", "validHrData", "Ljava/util/ArrayList;", "Lcom/samsung/health/data/TrackedData;", "getValidHrData", "Lkotlin/collections/ArrayList;", "hasCapabilities", "isHRValid", "hrStatus", "setListener", "", "listener", "Lcom/samsung/android/service/health/tracking/HealthTracker$TrackerEventListener;", "stopTracking", "track", "Lkotlinx/coroutines/flow/Flow;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trimDataList", "unsetListener", "wear_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class TrackingRepositoryImpl implements com.samsung.health.hrdatatransfer.data.TrackingRepository {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection healthTrackingServiceConnection = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.android.service.health.tracking.data.HealthTrackerType trackingType = com.samsung.android.service.health.tracking.data.HealthTrackerType.HEART_RATE_CONTINUOUS;
    private boolean listenerSet = false;
    @org.jetbrains.annotations.Nullable()
    private com.samsung.android.service.health.tracking.HealthTrackingService healthTrackingService;
    @org.jetbrains.annotations.NotNull()
    private java.util.HashMap<java.lang.String, java.lang.Integer> errors;
    private final int maxValuesToKeep = 40;
    @org.jetbrains.annotations.Nullable()
    private com.samsung.android.service.health.tracking.HealthTracker heartRateTracker;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.samsung.health.data.TrackedData> validHrData;
    
    @javax.inject.Inject()
    public TrackingRepositoryImpl(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection healthTrackingServiceConnection, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.Integer> getErrors() {
        return null;
    }
    
    public final void setErrors(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Integer> p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.ArrayList<com.samsung.health.data.TrackedData> getValidHrData() {
        return null;
    }
    
    private final boolean isHRValid(int hrStatus) {
        return false;
    }
    
    private final void trimDataList() {
    }
    
    @java.lang.Override()
    @kotlinx.coroutines.ExperimentalCoroutinesApi()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object track(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.samsung.health.hrdatatransfer.data.TrackerMessage>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public void stopTracking() {
    }
    
    private final void unsetListener() {
    }
    
    private final void setListener(com.samsung.android.service.health.tracking.HealthTracker.TrackerEventListener listener) {
    }
    
    @java.lang.Override()
    public boolean hasCapabilities() {
        return false;
    }
}