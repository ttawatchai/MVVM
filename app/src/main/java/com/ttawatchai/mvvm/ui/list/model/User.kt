package com.ttawatchai.mvvm.ui.list.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class User(
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "email")
    val email: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String
)