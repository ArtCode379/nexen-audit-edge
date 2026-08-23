package nexenstrategy.technology.nexenauditedge

import android.app.Application
//[ANY][import_PrepRepository]
import nexenstrategy.technology.nexenauditedge.di.dataModule
import nexenstrategy.technology.nexenauditedge.di.dispatcherModule
import nexenstrategy.technology.nexenauditedge.di.viewModule
//[COMMON][import_DiModule]
//[REFERRER][import_InstallReferrerManager]
//[APPSFLYER][imports_AppsFlyer]
//[FIREBASE][import_FirebaseMessaging]
//[FIREBASE][imports_coroutines]
//[ANY][import_getKoin]
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApplication : Application() {
    //[FIREBASE][appScope]

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApplication)
            modules(appModules)
        }

        //[ANY][repository]

        //[APPSFLYER][devKey]

        //[APPSFLYER][appsFlyerSettings]

        //[REFERRER][referrerManagerSettings]

        //[APPSFLYER][appsFlyerId]

        //[FIREBASE][FirebaseMessaging]
    }
}