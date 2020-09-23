package com.dev777popov.noteskotlingeek

import android.app.Application
import com.dev777popov.noteskotlingeek.data.di.appModule
import com.dev777popov.noteskotlingeek.data.di.mainModule
import com.dev777popov.noteskotlingeek.data.di.noteModule
import com.dev777popov.noteskotlingeek.data.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, splashModule, mainModule, noteModule)
        }
    }
}