package com.samsung.health.hrdatatransfer.presentation;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.samsung.android.service.health.tracking.HealthTrackerException;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.ConnectionMessage;
import com.samsung.health.hrdatatransfer.data.TrackerMessage;
import com.samsung.health.hrdatatransfer.domain.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/samsung/health/hrdatatransfer/presentation/ConnectionState;", "", "connected", "", "message", "", "connectionException", "Lcom/samsung/android/service/health/tracking/HealthTrackerException;", "(ZLjava/lang/String;Lcom/samsung/android/service/health/tracking/HealthTrackerException;)V", "getConnected", "()Z", "getConnectionException", "()Lcom/samsung/android/service/health/tracking/HealthTrackerException;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "wear_debug"})
public final class ConnectionState {
    private final boolean connected = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    @org.jetbrains.annotations.Nullable()
    private final com.samsung.android.service.health.tracking.HealthTrackerException connectionException = null;
    
    public ConnectionState(boolean connected, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    com.samsung.android.service.health.tracking.HealthTrackerException connectionException) {
        super();
    }
    
    public final boolean getConnected() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.samsung.android.service.health.tracking.HealthTrackerException getConnectionException() {
        return null;
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.samsung.android.service.health.tracking.HealthTrackerException component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.presentation.ConnectionState copy(boolean connected, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    com.samsung.android.service.health.tracking.HealthTrackerException connectionException) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}