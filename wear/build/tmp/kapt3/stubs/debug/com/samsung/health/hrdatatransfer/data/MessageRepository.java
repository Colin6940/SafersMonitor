package com.samsung.health.hrdatatransfer.data;

import com.google.android.gms.wearable.Node;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/samsung/health/hrdatatransfer/data/MessageRepository;", "", "sendMessage", "", "message", "", "node", "Lcom/google/android/gms/wearable/Node;", "messagePath", "(Ljava/lang/String;Lcom/google/android/gms/wearable/Node;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "wear_debug"})
public abstract interface MessageRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.wearable.Node node, @org.jetbrains.annotations.NotNull()
    java.lang.String messagePath, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}