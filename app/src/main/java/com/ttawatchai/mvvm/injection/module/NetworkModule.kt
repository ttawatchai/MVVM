package com.ttawatchai.mvvm.injection.module

import android.util.Log
import com.google.gson.GsonBuilder
import com.ttawatchai.mvvm.data.api.ApiService
import com.ttawatchai.mvvm.utills.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
object NetworkModule {


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiService(@Named("provideRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    @Named("provideRetrofit")
    internal fun provideRetrofitInterface(): Retrofit {
        val url: String = BASE_URL

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Content-type", "application/json; charset=utf-8")
//                .header("apiKey", API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val client = httpClient.build()
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


}
