package com.ttawatchai.mvvm.service.repository

import android.content.Context
import com.ttawatchai.mvvm.service.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(val context: Context, private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

}