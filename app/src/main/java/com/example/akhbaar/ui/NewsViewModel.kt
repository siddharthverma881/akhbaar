package com.example.akhbaar.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.akhbaar.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.akhbaar.data.model.Article
import com.example.akhbaar.ui.base.UiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState : StateFlow<UiState<List<Article>>> = _uiState

    init {
        Log.e("NewsViewModel","Inside init on View Model----------------")
        fetchNews()
    }

    private fun fetchNews(){
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.getHeadLines()
                .catch {
                    it.stackTrace
                    _uiState.value = UiState.Error(it.message?: "")
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}