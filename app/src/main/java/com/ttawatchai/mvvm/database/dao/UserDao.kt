package com.ttawatchai.mvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anywheretogo.claimdi360.database.entity.UserInfoEntity


@Dao
interface UserDao {
    @Insert
    fun insert(allDataEntity: UserInfoEntity)



    @Query("DELETE FROM user_info")
    fun deleteAll()
}