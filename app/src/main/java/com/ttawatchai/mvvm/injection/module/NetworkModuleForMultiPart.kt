package com.ttawatchai.mvvm.injection.module

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
object NetworkModuleForMultiPart {


//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideCarCrashApi(@Named("provideRetrofit2") retrofit: Retrofit): CarCrashApi {
//        return retrofit.create(CarCrashApi::class.java)
//    }


    @Provides
    @Reusable
    @JvmStatic
    @Named("provideRetrofit2")
    internal fun provideRetrofitInterface(): Retrofit {
        val url: String = ""

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
        var gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
//            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}