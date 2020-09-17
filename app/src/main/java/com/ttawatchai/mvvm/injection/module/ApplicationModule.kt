package com.ttawatchai.mvvm.injection.module

import android.app.Application
import android.content.Context
import com.ttawatchai.mvvm.MainApplication

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by iChris on 7/24/17.
 */
@Module
class ApplicationModule(val application: MainApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun proViderPrefHelper(context: Context): PrefsHelper = PrefsHelper(context)

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(context: Context): Cache {
        val CACHE_SIZE = (5 * 1024 * 1024).toLong()
        return Cache(context.cacheDir, CACHE_SIZE)
    }

}