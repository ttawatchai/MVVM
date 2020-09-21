package com.ttawatchai.mvvm.feature.listdetail

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.injection.database.dao.UserDao
import com.ttawatchai.mvvm.feature.listdata.model.User
import javax.inject.Inject

class DetailViewModel : ViewModel() {

    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var dao: UserDao
    val user = ObservableField<User>()
    val email = ObservableField<String>("test@email.com")
    val isFav = ObservableField<Boolean>(false)

    fun updateDatabase() {
        var newUser = user.get()?.apply {
            Log.d("data_old", this?.fav.toString())
            this.fav = !this.fav
        }.also {
            Log.d("data", it?.fav.toString())
            dao.updatDevice(it!!)
        }
    }
}

