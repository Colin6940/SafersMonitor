package com.samsung.health.hrdatatransfer.domain;

import com.samsung.health.hrdatatransfer.data.ConnectionMessage;
import com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/samsung/health/hrdatatransfer/domain/MakeConnectionToHealthTrackingServiceUseCase;", "", "healthTrackingServiceConnection", "Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;", "(Lcom/samsung/health/hrdatatransfer/data/HealthTrackingServiceConnection;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/samsung/health/hrdatatransfer/data/ConnectionMessage;", "wear_debug"})
public final class MakeConnectionToHealthTrackingServiceUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection healthTrackingServiceConnection = null;
    
    @javax.inject.Inject()
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    public MakeConnectionToHealthTrackingServiceUseCase(@org.jetbrains.annotations.NotNull()
    com.samsung.health.hrdatatransfer.data.HealthTrackingServiceConnection healthTrackingServiceConnection) {
        super();
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.samsung.health.hrdatatransfer.data.ConnectionMessage> invoke() {
        return null;
    }
}