package nexenstrategy.technology.nexenauditedge.data.repository

import nexenstrategy.technology.nexenauditedge.data.datastore.LMAQNOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LMAQNOnboardingRepo(
    private val lmaqnOnboardingStoreManager: LMAQNOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return lmaqnOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            lmaqnOnboardingStoreManager.setOnboardedState(state)
        }
    }
}