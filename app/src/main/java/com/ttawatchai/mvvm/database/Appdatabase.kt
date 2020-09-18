package com.ttawatchai.mvvm.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ttawatchai.mvvm.database.dao.UserDao
import com.ttawatchai.mvvm.ui.list.model.User
import java.util.*

@Database(
    version = AppDatabase.VERSION,
    entities = [User::class],
    exportSchema = false
)
@TypeConverters(AppDatabase.InfoConverters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
        private var sInstance: AppDatabase? = null

        private val MIGRATION_1_to_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `image_car_crash` (`driverId` STRING ,`typeImage` STRING,`imageUrl` STRING,`id` INTEGER, PRIMARY KEY(`id`))"
//                )
            }
        }

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "content")
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_to_2)
                    .build()
            }
            return sInstance!!
        }
    }


    abstract fun userDao(): UserDao
    class InfoConverters {
        companion object {
            @TypeConverter
            @JvmStatic
            fun fromTimestamp(value: String?): Objects? {
                val notesType = object : TypeToken<Objects>() {}.type
                return if (value == null) null else Gson().fromJson<Objects>(value, notesType)
            }

            @TypeConverter
            @JvmStatic
            fun dateToTimestamp(stat: Objects?): String? {
                return if (stat == null) null else Gson().toJson(stat)
            }
        }
    }

}