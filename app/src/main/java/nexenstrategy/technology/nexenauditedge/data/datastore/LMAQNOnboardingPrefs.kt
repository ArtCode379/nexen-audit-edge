package nexenstrategy.technology.nexenauditedge.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val LMAQN_PREFS_NAME = "lmaqn_prefs"

val Context.lmaqnOnboardingStore by preferencesDataStore(name = LMAQN_PREFS_NAME)

class LMAQNOnboardingPrefs(
    private val context: Context
) {
    val onboardedStateFlow: Flow<Boolean?> = context.lmaqnOnboardingStore.data.map { prefs ->
        prefs[ONBOARDED_STATE_KEY]
    }

    suspend fun setOnboardedState(state: Boolean) {
        context.lmaqnOnboardingStore.edit { prefs ->
            prefs[ONBOARDED_STATE_KEY] = state
        }
    }

    companion object {
        private val ONBOARDED_STATE_KEY = booleanPreferencesKey("onboardedState")
    }
}