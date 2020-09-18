package com.ttawatchai.mvvm.ui.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.data.repository.AlertRepository
import com.ttawatchai.mvvm.injection.base.BaseViewModel
import com.ttawatchai.mvvm.injection.module.PrefsHelper
import com.ttawatchai.networklibrary.model.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ListViewModel : ViewModel(){

    init {
        MainApplication.dataComponent.inject(this)
    }
    @Inject
    lateinit var repository: AlertRepository

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!",error = ""))
        }
    }

}