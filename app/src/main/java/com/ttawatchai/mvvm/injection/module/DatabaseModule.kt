package com.ttawatchai.mvvm.injection.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ttawatchai.mvvm.injection.database.AppDatabase
import com.ttawatchai.mvvm.injection.database.dao.UserDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by cytan on 2/12/2018.
 */
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase = Room
        .databaseBuilder(application as Context, AppDatabase::class.java, "instant.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

}