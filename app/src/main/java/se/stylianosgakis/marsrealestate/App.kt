package se.stylianosgakis.marsrealestate

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import se.stylianosgakis.marsrealestate.di.networkModule
import se.stylianosgakis.marsrealestate.di.glideModule
import se.stylianosgakis.marsrealestate.di.viewModelsModule
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        configureLogging()
        configureDependencyInjection()
    }

    private fun configureLogging() {
        Timber.plant(Timber.DebugTree())
    }

    private fun configureDependencyInjection() {
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, viewModelsModule, glideModule))
        }
    }
}
