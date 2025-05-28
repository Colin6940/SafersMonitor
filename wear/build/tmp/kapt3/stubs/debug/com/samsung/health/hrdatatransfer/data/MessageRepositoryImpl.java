package com.samsung.health.hrdatatransfer.data;

import android.util.Log;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.Node;
import java.nio.charset.Charset;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/MessageRepositoryImpl;", "Lcom/samsung/health/hrdatatransfer/data/MessageRepository;", "messageClient", "Lcom/google/android/gms/wearable/MessageClient;", "(Lcom/google/android/gms/wearable/MessageClient;)V", "sendMessage", "", "message", "", "node", "Lcom/google/android/gms/wearable/Node;", "messagePath", "(Ljava/lang/String;Lcom/google/android/gms/wearable/Node;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public final class MessageRepositoryImpl implements com.samsung.health.hrdatatransfer.data.MessageRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.wearable.MessageClient messageClient = null;
    
    @javax.inject.Inject()
    public MessageRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.MessageClient messageClient) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.Node node, @org.jetbrains.annotations.NotNull()
    java.lang.String messagePath, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}