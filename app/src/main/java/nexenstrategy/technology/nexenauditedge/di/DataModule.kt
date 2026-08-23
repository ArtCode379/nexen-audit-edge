package nexenstrategy.technology.nexenauditedge.di

import nexenstrategy.technology.nexenauditedge.data.repository.BookingRepository
import nexenstrategy.technology.nexenauditedge.data.repository.LMAQNOnboardingRepo
import nexenstrategy.technology.nexenauditedge.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        LMAQNOnboardingRepo(
            lmaqnOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}