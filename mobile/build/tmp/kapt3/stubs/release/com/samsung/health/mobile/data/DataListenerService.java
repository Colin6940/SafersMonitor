package com.samsung.health.mobile.data;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.Gson;
import com.samsung.health.mobile.data.model.HeartRateData;
import com.samsung.health.mobile.presentation.MainActivity;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/samsung/health/mobile/data/DataListenerService;", "Lcom/google/android/gms/wearable/WearableListenerService;", "()V", "firebaseHeartRateRepository", "LFirebaseHeartRateRepository;", "getFirebaseHeartRateRepository", "()LFirebaseHeartRateRepository;", "setFirebaseHeartRateRepository", "(LFirebaseHeartRateRepository;)V", "userManager", "Lcom/samsung/health/mobile/data/UserManager;", "getUserManager", "()Lcom/samsung/health/mobile/data/UserManager;", "setUserManager", "(Lcom/samsung/health/mobile/data/UserManager;)V", "onMessageReceived", "", "messageEvent", "Lcom/google/android/gms/wearable/MessageEvent;", "parseHeartRateData", "Lcom/samsung/health/mobile/data/model/HeartRateData;", "jsonString", "", "mobile_release"})
public final class DataListenerService extends com.google.android.gms.wearable.WearableListenerService {
    @javax.inject.Inject()
    public FirebaseHeartRateRepository firebaseHeartRateRepository;
    @javax.inject.Inject()
    public com.samsung.health.mobile.data.UserManager userManager;
    
    public DataListenerService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final FirebaseHeartRateRepository getFirebaseHeartRateRepository() {
        return null;
    }
    
    public final void setFirebaseHeartRateRepository(@org.jetbrains.annotations.NotNull()
    FirebaseHeartRateRepository p0) {
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
    
    private final com.samsung.health.mobile.data.model.HeartRateData parseHeartRateData(java.lang.String jsonString) {
        return null;
    }
}