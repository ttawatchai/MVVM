package com.ttawatchai.mvvm.service.api

import com.ttawatchai.mvvm.service.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}