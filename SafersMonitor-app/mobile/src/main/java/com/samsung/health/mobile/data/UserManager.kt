package com.samsung.health.mobile.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.android.identity.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val KEY_USER_ID = "user_id"
    }

    fun getCurrentUserId(): String {
        var userId = sharedPreferences.getString(KEY_USER_ID, null)
        if (userId == null) {
            userId = UUID.randomUUID().toString()
            sharedPreferences.edit() { putString(KEY_USER_ID, userId) }
        }
        return userId
    }
}

