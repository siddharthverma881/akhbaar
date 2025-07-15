package com.example.akhbaar.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.akhbaar.data.repository.NewsRepository
import com.example.akhbaar.di.ActivityContext
import com.example.akhbaar.ui.NewsViewModel
import com.example.akhbaar.ui.base.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun getActivityContext(): Context {
        return activity
    }

    @Provides
    fun getViewModel(repository: NewsRepository): NewsViewModel {
        return ViewModelProvider(
            activity,
            ViewModelProviderFactory(NewsViewModel::class) {
                NewsViewModel(repository)
            })[NewsViewModel::class.java]
    }

}