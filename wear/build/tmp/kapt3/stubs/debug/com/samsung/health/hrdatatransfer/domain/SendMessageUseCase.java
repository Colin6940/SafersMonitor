package com.samsung.health.hrdatatransfer.domain;

import android.util.Log;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.MessageRepository;
import com.samsung.health.hrdatatransfer.data.TrackingRepository;
import kotlinx.coroutines.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\r\u001a\u00020\u000e2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0086B\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u001aR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/samsung/health/hrdatatransfer/domain/SendMessageUseCase;", "", "messageRepository", "Lcom/samsung/health/hrdatatransfer/data/MessageRepository;", "trackingRepository", "Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;", "getCapableNodes", "Lcom/samsung/health/hrdatatransfer/domain/GetCapableNodes;", "(Lcom/samsung/health/hrdatatransfer/data/MessageRepository;Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;Lcom/samsung/health/hrdatatransfer/domain/GetCapableNodes;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "periodicSendingJob", "Lkotlinx/coroutines/Job;", "encodeMessage", "", "trackedData", "Ljava/util/ArrayList;", "Lcom/samsung/health/data/TrackedData;", "Lkotlin/collections/ArrayList;", "invoke", "", "periodic", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startPeriodicSending", "", "stopPeriodicSending", "wear_debug"})
public final class SendMessageUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.data.MessageRepository messageRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.data.TrackingRepository trackingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.domain.GetCapableNodes getCapableNodes = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job periodicSendingJob;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    
    @javax.inject.Inject()
    public SendMessageUseCase(@org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.data.MessageRepository messageRepository, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.data.TrackingRepository trackingRepository, @org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.domain.GetCapableNodes getCapableNodes) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(boolean periodic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final java.lang.Object sendOnce(kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final void startPeriodicSending() {
    }
    
    public final void stopPeriodicSending() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String encodeMessage(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.samsung.health.data.TrackedData> trackedData) {
        return null;
    }
}