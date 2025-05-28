package com.samsung.health.mobile.data.repository;

import com.google.firebase.database.*;
import com.samsung.health.mobile.data.model.HeartRateData;
import kotlinx.coroutines.*;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J=\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102(\u0010\u0018\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019\u00a2\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001d"}, d2 = {"Lcom/samsung/health/mobile/data/repository/FirebaseHeartRateRepository;", "", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dataCollectionJob", "Lkotlinx/coroutines/Job;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "heartRateRef", "Lcom/google/firebase/database/DatabaseReference;", "observeHeartRates", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/samsung/health/mobile/data/model/HeartRateData;", "userId", "", "saveHeartRate", "Lkotlin/Result;", "", "heartRateData", "saveHeartRate-gIAlu-s", "(Lcom/samsung/health/mobile/data/model/HeartRateData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startPeriodicDataCollection", "onNewData", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "stopPeriodicDataCollection", "mobile_debug"})
public final class FirebaseHeartRateRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference heartRateRef = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job dataCollectionJob;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    
    @javax.inject.Inject()
    public FirebaseHeartRateRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.samsung.health.mobile.data.model.HeartRateData>> observeHeartRates(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    public final void startPeriodicDataCollection(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.util.List<com.samsung.health.mobile.data.model.HeartRateData>, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> onNewData) {
    }
    
    public final void stopPeriodicDataCollection() {
    }
}