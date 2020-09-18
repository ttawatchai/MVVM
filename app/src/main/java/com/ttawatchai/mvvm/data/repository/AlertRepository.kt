package com.ttawatchai.mvvm.data.repository

import android.content.Context
import com.ttawatchai.mvvm.data.api.ApiService
import javax.inject.Inject

class AlertRepository @Inject constructor(val context: Context, private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

}