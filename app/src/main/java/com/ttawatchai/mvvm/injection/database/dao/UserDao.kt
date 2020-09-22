package com.ttawatchai.mvvm.injection.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ttawatchai.mvvm.feature.listdata.model.User


@Dao
interface UserDao {
    @Insert
    fun insert(allDataEntity: List<User>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatDevice(item: User): Int

    @Query("SELECT * FROM user_info  ORDER BY fav DESC, id ASC ")
    fun getDataSourcefactory(): DataSource.Factory<Int, User>

    @Query("SELECT COUNT(id) FROM user_info WHERE fav = 1")
    fun getCount(): LiveData<Int>

    @Query("DELETE FROM user_info")
    fun deleteAll()
}