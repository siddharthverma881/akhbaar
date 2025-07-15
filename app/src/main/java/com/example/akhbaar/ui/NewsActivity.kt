package com.example.akhbaar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.akhbaar.AkhbaarApplication
import com.example.akhbaar.R
import com.example.akhbaar.di.component.DaggerActivityComponent
import com.example.akhbaar.di.module.ActivityModule
import com.example.akhbaar.ui.base.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        installDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerObserver()

    }

    private fun registerObserver(){
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when(it){
                    is UiState.Success -> {
                        Log.e("NewsActivity", "${it.data}")
                    }
                    is UiState.Loading -> {

                    }
                    is UiState.Error -> {

                    }
                }
            }
        }
    }

    private fun installDependencies(){
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as AkhbaarApplication).application)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}