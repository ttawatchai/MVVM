package com.ttawatchai.mvvm.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.injection.module.PrefsHelper
import javax.inject.Inject

abstract class BaseFragment : Fragment(){
    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var prefsHelper: PrefsHelper


}