package com.example.akhbaar

import android.app.Application
import com.example.akhbaar.di.component.ApplicationComponent
import com.example.akhbaar.di.component.DaggerApplicationComponent
import com.example.akhbaar.di.module.ApplicationModule

class AkhbaarApplication: Application() {

    lateinit var application: ApplicationComponent

    override fun onCreate() {
        getDependencies()
        super.onCreate()
    }

    private fun getDependencies(){
        application = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        application.inject(this)
    }
}