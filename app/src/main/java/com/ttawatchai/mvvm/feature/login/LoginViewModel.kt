package com.ttawatchai.mvvm.feature.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.database.dao.UserDao
import com.ttawatchai.mvvm.injection.module.PrefsHelper
import com.ttawatchai.mvvm.service.model.User
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    init {
        MainApplication.dataComponent.inject(this)
    }

    val user = MutableLiveData<String>()

}

