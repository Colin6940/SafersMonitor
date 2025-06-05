package com.samsung.health.hrdatatransfer.presentation.ui;

import android.util.Log;
import android.widget.Toast;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.wear.compose.material.ButtonDefaults;
import com.samsung.health.hrdatatransfer.R;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0082\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H\u0007\u001a\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0007\u001a\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"TAG", "", "MainScreen", "", "connected", "", "connectionMessage", "trackingRunning", "trackingError", "trackingMessage", "valueHR", "valueIBI", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "isPeriodicSending", "onStart", "Lkotlin/Function0;", "onStop", "onSend", "ShowConnectionMessage", "ShowToast", "message", "wear_debug"})
public final class MainScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "MainScreen";
    
    @androidx.compose.runtime.Composable()
    public static final void MainScreen(boolean connected, @org.jetbrains.annotations.NotNull()
    java.lang.String connectionMessage, boolean trackingRunning, boolean trackingError, @org.jetbrains.annotations.NotNull()
    java.lang.String trackingMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String valueHR, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> valueIBI, boolean isPeriodicSending, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStart, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStop, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSend) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShowConnectionMessage(boolean connected, @org.jetbrains.annotations.NotNull()
    java.lang.String connectionMessage) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShowToast(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
}