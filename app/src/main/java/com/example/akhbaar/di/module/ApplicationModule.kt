package com.example.akhbaar.di.module

import com.example.akhbaar.AkhbaarApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: AkhbaarApplication) {

    @Provides
    fun getContext(): AkhbaarApplication {
        return application
    }
}