package com.samsung.health.mobile.data;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.samsung.health.mobile.data.model.HeartRateData;
import com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository;
import com.samsung.health.mobile.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001b"}, d2 = {"Lcom/samsung/health/mobile/data/DataListenerService;", "Lcom/google/android/gms/wearable/WearableListenerService;", "()V", "firebaseHeartRateRepository", "Lcom/samsung/health/mobile/data/repository/FirebaseHeartRateRepository;", "getFirebaseHeartRateRepository", "()Lcom/samsung/health/mobile/data/repository/FirebaseHeartRateRepository;", "setFirebaseHeartRateRepository", "(Lcom/samsung/health/mobile/data/repository/FirebaseHeartRateRepository;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "userManager", "Lcom/samsung/health/mobile/data/UserManager;", "getUserManager", "()Lcom/samsung/health/mobile/data/UserManager;", "setUserManager", "(Lcom/samsung/health/mobile/data/UserManager;)V", "onDestroy", "", "onMessageReceived", "messageEvent", "Lcom/google/android/gms/wearable/MessageEvent;", "parseHeartRateDataList", "", "Lcom/samsung/health/mobile/data/model/HeartRateData;", "jsonString", "", "mobile_debug"})
public final class DataListenerService extends com.google.android.gms.wearable.WearableListenerService {
    @javax.inject.Inject()
    public com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository firebaseHeartRateRepository;
    @javax.inject.Inject()
    public com.samsung.health.mobile.data.UserManager userManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    
    public DataListenerService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository getFirebaseHeartRateRepository() {
        return null;
    }
    
    public final void setFirebaseHeartRateRepository(@org.jetbrains.annotations.NotNull()
    com.samsung.health.mobile.data.repository.FirebaseHeartRateRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.samsung.health.mobile.data.UserManager getUserManager() {
        return null;
    }
    
    public final void setUserManager(@org.jetbrains.annotations.NotNull()
    com.samsung.health.mobile.data.UserManager p0) {
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.MessageEvent messageEvent) {
    }
    
    private final java.util.List<com.samsung.health.mobile.data.model.HeartRateData> parseHeartRateDataList(java.lang.String jsonString) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
}