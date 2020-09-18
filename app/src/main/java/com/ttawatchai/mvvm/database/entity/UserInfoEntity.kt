package com.ttawatchai.mvvm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
class UserInfoEntity constructor(carId: String, name: String, lastName: String, pathImg: String) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "car_id")
    var carId: String? = carId

    @ColumnInfo(name = "name")
    var name: String? = name

    @ColumnInfo(name = "last_name")
    var lastName: String? = lastName

    @ColumnInfo(name = "path_img")
    var pathImg: String? = pathImg

}

