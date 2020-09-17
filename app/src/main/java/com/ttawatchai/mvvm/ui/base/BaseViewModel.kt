package com.ttawatchai.mvvm.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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