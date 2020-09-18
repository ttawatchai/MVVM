package com.ttawatchai.mvvm.injection.base

import androidx.lifecycle.ViewModel
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.injection.module.PrefsHelper
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(){
    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var prefsHelper: PrefsHelper


}