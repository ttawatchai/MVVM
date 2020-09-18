package com.ttawatchai.mvvm.injection

import android.app.Application
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.injection.module.ApplicationModule
import com.ttawatchai.mvvm.injection.module.DatabaseModule
import com.ttawatchai.mvvm.injection.module.NetworkModule
import com.ttawatchai.mvvm.injection.module.NetworkModuleForMultiPart
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.BaseViewModel
import com.ttawatchai.mvvm.ui.list.ui.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class, NetworkModuleForMultiPart::class, ApplicationModule::class, DatabaseModule::class]
)
interface ApplicationComponent {
    fun inject(app: Application)
    fun inject(mainApplication: MainApplication)
    fun inject(baseFragment: BaseFragment)
    fun inject(baseFragment: BaseViewModel)
    fun inject(viewmodel: ListViewModel)
}
