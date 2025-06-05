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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010*\u001a\u00020+H\u0014J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.H\u0002J\u0006\u0010/\u001a\u00020+J\u0006\u00100\u001a\u00020+J\u0006\u00101\u001a\u00020+J\b\u00102\u001a\u00020+H\u0002J\u0006\u00103\u001a\u00020+R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020!8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0016\u00a8\u00064"}, d2 = {"Lcom/samsung/health/hrdatatransfer/presentation/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "makeConnectionToHealthTrackingServiceUseCase", "Lcom/samsung/health/hrdatatransfer/domain/MakeConnectionToHealthTrackingServiceUseCase;", "sendMessageUseCase", "Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;", "stopTrackingUseCase", "Lcom/samsung/health/hrdatatransfer/domain/StopTrackingUseCase;", "areTrackingCapabilitiesAvailableUseCase", "Lcom/samsung/health/hrdatatransfer/domain/AreTrackingCapabilitiesAvailableUseCase;", "(Lcom/samsung/health/hrdatatransfer/domain/MakeConnectionToHealthTrackingServiceUseCase;Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;Lcom/samsung/health/hrdatatransfer/domain/StopTrackingUseCase;Lcom/samsung/health/hrdatatransfer/domain/AreTrackingCapabilitiesAvailableUseCase;)V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/samsung/health/hrdatatransfer/presentation/ConnectionState;", "_messageSentToast", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "_trackingState", "Lcom/samsung/health/hrdatatransfer/presentation/TrackingState;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "currentHR", "", "currentIBI", "Ljava/util/ArrayList;", "", "messageSentToast", "Lkotlinx/coroutines/flow/SharedFlow;", "getMessageSentToast", "()Lkotlinx/coroutines/flow/SharedFlow;", "trackHeartRateUseCase", "Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;", "getTrackHeartRateUseCase", "()Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;", "setTrackHeartRateUseCase", "(Lcom/samsung/health/hrdatatransfer/domain/TrackHeartRateUseCase;)V", "trackingJob", "Lkotlinx/coroutines/Job;", "trackingState", "getTrackingState", "onCleared", "", "processExerciseUpdate", "trackedData", "Lcom/samsung/health/data/TrackedData;", "sendMessage", "setUpTracking", "startTracking", "stopPeriodicSending", "stopTracking", "wear_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase makeConnectionToHealthTrackingServiceUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.domain.SendMessageUseCase sendMessageUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase stopTrackingUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase areTrackingCapabilitiesAvailableUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Boolean> _messageSentToast = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.Boolean> messageSentToast = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> _trackingState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> trackingState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> connectionState = null;
    @javax.inject.Inject()
    public com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase trackHeartRateUseCase;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentHR = "-";
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<java.lang.Integer> currentIBI;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job trackingJob;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.MakeConnectionToHealthTrackingServiceUseCase makeConnectionToHealthTrackingServiceUseCase, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.SendMessageUseCase sendMessageUseCase, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.StopTrackingUseCase stopTrackingUseCase, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.AreTrackingCapabilitiesAvailableUseCase areTrackingCapabilitiesAvailableUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.Boolean> getMessageSentToast() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.TrackingState> getTrackingState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.samsung.health.hrdatatransfer.presentation.ConnectionState> getConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase getTrackHeartRateUseCase() {
        return null;
    }
    
    public final void setTrackHeartRateUseCase(@org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.TrackHeartRateUseCase p0) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void stopTracking() {
    }
    
    public final void setUpTracking() {
    }
    
    public final void sendMessage() {
    }
    
    private final void stopPeriodicSending() {
    }
    
    private final void processExerciseUpdate(com.samsung.health.data.TrackedData trackedData) {
    }
    
    public final void startTracking() {
    }
}