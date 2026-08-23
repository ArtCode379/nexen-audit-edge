package nexenstrategy.technology.nexenauditedge.di

import nexenstrategy.technology.nexenauditedge.data.datastore.LMAQNOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { LMAQNOnboardingPrefs(androidContext()) }
}