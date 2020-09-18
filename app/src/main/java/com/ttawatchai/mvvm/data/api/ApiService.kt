package com.ttawatchai.mvvm.data.api

import com.ttawatchai.mvvm.ui.list.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}