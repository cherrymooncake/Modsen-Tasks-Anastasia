package com.cherrymooncake.modsen_tasks_anastasia

import android.app.Application
import com.cherrymooncake.modsen_tasks_anastasia.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                appModule
            )
        }
    }
}