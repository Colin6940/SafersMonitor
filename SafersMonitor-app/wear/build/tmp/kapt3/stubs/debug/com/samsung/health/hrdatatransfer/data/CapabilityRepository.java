package com.samsung.health.hrdatatransfer.data;

import com.google.android.gms.wearable.Node;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0007J6\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00052\u0006\u0010\t\u001a\u00020\u00062\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/CapabilityRepository;", "", "getCapabilitiesForReachableNodes", "", "Lcom/google/android/gms/wearable/Node;", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNodesForCapability", "capability", "allCapabilities", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public abstract interface CapabilityRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNodesForCapability(@org.jetbrains.annotations.NotNull()
    java.lang.String capability, @org.jetbrains.annotations.NotNull()
    java.util.Map<com.google.android.gms.wearable.Node, ? extends java.util.Set<java.lang.String>> allCapabilities, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<? extends com.google.android.gms.wearable.Node>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCapabilitiesForReachableNodes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<com.google.android.gms.wearable.Node, ? extends java.util.Set<java.lang.String>>> $completion);
}