package com.example.akhbaar.di.component

import com.example.akhbaar.AkhbaarApplication
import com.example.akhbaar.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AkhbaarApplication)
}