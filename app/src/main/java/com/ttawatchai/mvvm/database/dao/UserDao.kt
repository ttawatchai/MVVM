package com.ttawatchai.mvvm.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ttawatchai.mvvm.ui.list.model.User


@Dao
interface UserDao {
    @Insert
    fun insert(allDataEntity: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatDevice(item: User): Int

    @Query("SELECT * FROM user_info  ORDER BY fav DESC, id ASC ")
    fun getDataSourcefactory(): DataSource.Factory<Int, User>

    @Query("DELETE FROM user_info")
    fun deleteAll()
}