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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "", "()V", "ConnectionEndedMessage", "ConnectionFailedMessage", "ConnectionSuccessMessage", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionEndedMessage;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionFailedMessage;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionSuccessMessage;", "wear_debug"})
public abstract class ConnectionMessage {
    
    private ConnectionMessage() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionEndedMessage;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "()V", "wear_debug"})
    public static final class ConnectionEndedMessage extends com.samsung.health.hrdatatransfer.data.ConnectionMessage {
        @org.jetbrains.annotations.NotNull()
        public static final com.samsung.health.hrdatatransfer.data.ConnectionMessage.ConnectionEndedMessage INSTANCE = null;
        
        private ConnectionEndedMessage() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionFailedMessage;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "exception", "Lcom/samsung/android/service/health/tracking/HealthTrackerException;", "(Lcom/samsung/android/service/health/tracking/HealthTrackerException;)V", "getException", "()Lcom/samsung/android/service/health/tracking/HealthTrackerException;", "wear_debug"})
    public static final class ConnectionFailedMessage extends com.samsung.health.hrdatatransfer.data.ConnectionMessage {
        @org.jetbrains.annotations.Nullable()
        private final com.samsung.android.service.health.tracking.HealthTrackerException exception = null;
        
        public ConnectionFailedMessage(@org.jetbrains.annotations.Nullable()
        com.samsung.android.service.health.tracking.HealthTrackerException exception) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.samsung.android.service.health.tracking.HealthTrackerException getException() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage$ConnectionSuccessMessage;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "()V", "wear_debug"})
    public static final class ConnectionSuccessMessage extends com.samsung.health.hrdatatransfer.data.ConnectionMessage {
        @org.jetbrains.annotations.NotNull()
        public static final com.samsung.health.hrdatatransfer.data.ConnectionMessage.ConnectionSuccessMessage INSTANCE = null;
        
        private ConnectionSuccessMessage() {
        }
    }
}