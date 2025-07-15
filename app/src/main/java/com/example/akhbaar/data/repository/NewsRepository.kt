package com.example.akhbaar.data.repository

import android.util.Log
import com.example.akhbaar.data.api.ApiInterface
import com.example.akhbaar.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getHeadLines(): Flow<List<Article>> {
        return flow {
            emit(apiInterface.getHeadLines("us"))
        }.map {
            it.articles
        }
    }
}