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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "", "()V", "DataMessage", "FlushCompletedMessage", "TrackerErrorMessage", "TrackerWarningMessage", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$DataMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$FlushCompletedMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$TrackerErrorMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$TrackerWarningMessage;", "wear_debug"})
public abstract class TrackerMessage {
    
    private TrackerMessage() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$DataMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "trackedData", "Lcom/samsung/health/data/TrackedData;", "(Lcom/samsung/health/data/TrackedData;)V", "getTrackedData", "()Lcom/samsung/health/data/TrackedData;", "wear_debug"})
    public static final class DataMessage extends com.samsung.health.hrdatatransfer.data.TrackerMessage {
        @org.jetbrains.annotations.NotNull()
        private final com.samsung.health.data.TrackedData trackedData = null;
        
        public DataMessage(@org.jetbrains.annotations.NotNull()
        com.samsung.health.data.TrackedData trackedData) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.samsung.health.data.TrackedData getTrackedData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$FlushCompletedMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "()V", "wear_debug"})
    public static final class FlushCompletedMessage extends com.samsung.health.hrdatatransfer.data.TrackerMessage {
        @org.jetbrains.annotations.NotNull()
        public static final com.samsung.health.hrdatatransfer.data.TrackerMessage.FlushCompletedMessage INSTANCE = null;
        
        private FlushCompletedMessage() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$TrackerErrorMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "trackerError", "", "(Ljava/lang/String;)V", "getTrackerError", "()Ljava/lang/String;", "wear_debug"})
    public static final class TrackerErrorMessage extends com.samsung.health.hrdatatransfer.data.TrackerMessage {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackerError = null;
        
        public TrackerErrorMessage(@org.jetbrains.annotations.NotNull()
        java.lang.String trackerError) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackerError() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackerMessage$TrackerWarningMessage;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "trackerWarning", "", "(Ljava/lang/String;)V", "getTrackerWarning", "()Ljava/lang/String;", "wear_debug"})
    public static final class TrackerWarningMessage extends com.samsung.health.hrdatatransfer.data.TrackerMessage {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackerWarning = null;
        
        public TrackerWarningMessage(@org.jetbrains.annotations.NotNull()
        java.lang.String trackerWarning) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackerWarning() {
            return null;
        }
    }
}