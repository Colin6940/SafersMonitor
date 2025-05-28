package com.samsung.health.hrdatatransfer.domain;

import android.util.Log;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.MessageRepository;
import com.samsung.health.hrdatatransfer.data.TrackingRepository;
import kotlinx.coroutines.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0003"}, d2 = {"MESSAGE_PATH", "", "TAG", "wear_debug"})
public final class SendMessageUseCaseKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "SendMessageUseCase";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MESSAGE_PATH = "/msg";
}