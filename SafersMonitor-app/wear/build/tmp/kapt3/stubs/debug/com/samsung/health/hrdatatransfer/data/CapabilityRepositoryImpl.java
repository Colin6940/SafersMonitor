package com.samsung.health.hrdatatransfer.data;

import android.util.Log;
import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.Node;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006H\u0096@\u00a2\u0006\u0002\u0010\nJ6\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\b2\u0006\u0010\f\u001a\u00020\t2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006H\u0096@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/CapabilityRepositoryImpl;", "Lcom/samsung/health/hrdatatransfer/data/CapabilityRepository;", "capabilityClient", "Lcom/google/android/gms/wearable/CapabilityClient;", "(Lcom/google/android/gms/wearable/CapabilityClient;)V", "getCapabilitiesForReachableNodes", "", "Lcom/google/android/gms/wearable/Node;", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNodesForCapability", "capability", "allCapabilities", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public final class CapabilityRepositoryImpl implements com.samsung.health.hrdatatransfer.data.CapabilityRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.wearable.CapabilityClient capabilityClient = null;
    
    @javax.inject.Inject()
    public CapabilityRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.CapabilityClient capabilityClient) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCapabilitiesForReachableNodes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<com.google.android.gms.wearable.Node, ? extends java.util.Set<java.lang.String>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getNodesForCapability(@org.jetbrains.annotations.NotNull()
    java.lang.String capability, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.google.android.gms.wearable.Node, ? extends java.util.Set<java.lang.String>> allCapabilities, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<? extends com.google.android.gms.wearable.Node>> $completion) {
        return null;
    }
}