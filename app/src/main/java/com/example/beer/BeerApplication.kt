package com.example.beer

import android.app.Application
import com.example.beer.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BeerApplication)

            androidLogger()

            modules(
                viewModelModule
            )
        }
    }
}