package com.user.info.demo.app

import android.app.Application
import com.user.info.demo.di.ApplicationComponent
import com.user.info.demo.di.ApplicationModule
import com.user.info.demo.di.DaggerApplicationComponent

class UserApplication : Application() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}