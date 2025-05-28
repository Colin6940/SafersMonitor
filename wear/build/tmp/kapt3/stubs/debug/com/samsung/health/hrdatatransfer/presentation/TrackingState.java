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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\u0019\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003JU\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\tH\u00d6\u0001J\t\u0010 \u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006!"}, d2 = {"Lcom/samsung/health/hrdatatransfer/presentation/TrackingState;", "", "trackingRunning", "", "trackingError", "valueHR", "", "valueIBI", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "message", "isPeriodicSending", "(ZZLjava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Z)V", "()Z", "getMessage", "()Ljava/lang/String;", "getTrackingError", "getTrackingRunning", "getValueHR", "getValueIBI", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "wear_debug"})
public final class TrackingState {
    private final boolean trackingRunning = false;
    private final boolean trackingError = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String valueHR = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<java.lang.Integer> valueIBI = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    private final boolean isPeriodicSending = false;
    
    public TrackingState(boolean trackingRunning, boolean trackingError, @org.jetbrains.annotations.NotNull()
    java.lang.String valueHR, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> valueIBI, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean isPeriodicSending) {
        super();
    }
    
    public final boolean getTrackingRunning() {
        return false;
    }
    
    public final boolean getTrackingError() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getValueHR() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.Integer> getValueIBI() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final boolean isPeriodicSending() {
        return false;
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean component2() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.Integer> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.presentation.TrackingState copy(boolean trackingRunning, boolean trackingError, @org.jetbrains.annotations.NotNull()
    java.lang.String valueHR, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> valueIBI, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean isPeriodicSending) {
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