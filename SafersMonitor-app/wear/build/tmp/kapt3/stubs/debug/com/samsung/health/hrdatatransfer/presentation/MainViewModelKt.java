package com.samsung.health.hrdatatransfer.presentation;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.samsung.android.service.health.tracking.HealthTrackerException;
import com.samsung.health.data.TrackedData;
import com.samsung.health.hrdatatransfer.data.ConnectionMessage;
import com.samsung.health.hrdatatransfer.data.TrackerMessage;
import com.samsung.health.hrdatatransfer.domain.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0002"}, d2 = {"TAG", "", "wear_debug"})
public final class MainViewModelKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "MainViewModel";
}