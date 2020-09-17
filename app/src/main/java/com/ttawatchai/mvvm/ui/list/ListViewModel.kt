package com.ttawatchai.mvvm.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ttawatchai.mvvm.ui.base.BaseViewModel

class ListViewModel : BaseViewModel(){


    class Factory : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListViewModel() as T
        }
    }
}