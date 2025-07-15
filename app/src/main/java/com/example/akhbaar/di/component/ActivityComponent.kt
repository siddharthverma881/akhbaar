package com.example.akhbaar.di.component

import com.example.akhbaar.data.repository.NewsRepository
import com.example.akhbaar.di.ActivityScope
import com.example.akhbaar.di.module.ActivityModule
import com.example.akhbaar.ui.NewsActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: NewsActivity)

//    fun getNewsRepository(): NewsRepository

}