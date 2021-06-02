package com.example.beer

import android.app.Application
import android.content.Context
import com.example.beer.koin.viewModelModule
import com.example.beer.rest.BeerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BeerApplication : Application() {

    companion object {
        @kotlin.jvm.JvmStatic
        lateinit var instance: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@BeerApplication)

            androidLogger()

            modules(
                viewModelModule
            )
        }
        BeerRepository.init()
    }
}