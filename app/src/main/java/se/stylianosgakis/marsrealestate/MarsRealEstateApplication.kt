package se.stylianosgakis.marsrealestate

import android.app.Application
import timber.log.Timber

class MarsRealEstateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
