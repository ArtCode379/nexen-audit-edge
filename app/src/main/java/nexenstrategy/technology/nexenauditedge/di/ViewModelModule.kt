package nexenstrategy.technology.nexenauditedge.di

import nexenstrategy.technology.nexenauditedge.ui.viewmodel.BookingViewModel
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.CheckoutViewModel
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.LMAQNOnboardingVM
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.ServiceDetailsViewModel
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.ServiceViewModel
import nexenstrategy.technology.nexenauditedge.ui.viewmodel.LMAQNSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        LMAQNSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        LMAQNOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}