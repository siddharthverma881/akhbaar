package com.example.akhbaar.di.component

import android.content.Context
import com.example.akhbaar.AkhbaarApplication
import com.example.akhbaar.data.api.ApiInterface
import com.example.akhbaar.data.repository.NewsRepository
import com.example.akhbaar.di.ApplicationContext
import com.example.akhbaar.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AkhbaarApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): ApiInterface

    fun getNewsRepository(): NewsRepository
}