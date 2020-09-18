package com.ttawatchai.mvvm.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ttawatchai.mvvm.ui.list.model.User


@Dao
interface UserDao {
    @Insert
    fun insert(allDataEntity: List<User>)

    @Query("SELECT * FROM user_info ORDER BY id ")
    fun getDataSourcefactory(): DataSource.Factory<Int, User>

    @Query("DELETE FROM user_info")
    fun deleteAll()
}