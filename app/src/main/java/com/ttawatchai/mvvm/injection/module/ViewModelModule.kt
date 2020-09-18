package com.ttawatchai.mvvm.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ttawatchai.mvvm.injection.base.ViewModelFactory
import com.ttawatchai.mvvm.injection.base.ViewModelKey
import com.ttawatchai.mvvm.ui.list.ui.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindCountriesViewModel(listViewModel: ListViewModel): ViewModel

    /**
     * Binds ViewModels factory to provide ViewModels.
     */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
