package com.samsung.health.hrdatatransfer.data;

import com.samsung.health.data.TrackedData;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a6@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/TrackingRepository;", "", "getValidHrData", "Ljava/util/ArrayList;", "Lcom/samsung/health/data/TrackedData;", "Lkotlin/collections/ArrayList;", "hasCapabilities", "", "stopTracking", "", "track", "Lkotlinx/coroutines/flow/Flow;", "Lcom/samsung/health/hrdatatransfer/data/TrackerMessage;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public abstract interface TrackingRepository {
    
    public abstract boolean hasCapabilities();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object track(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends com.samsung.health.hrdatatransfer.data.TrackerMessage>> $completion);
    
    public abstract void stopTracking();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.ArrayList<com.samsung.health.data.TrackedData> getValidHrData();
}