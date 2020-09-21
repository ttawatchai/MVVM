package com.ttawatchai.mvvm.injection

import android.app.Application
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.injection.module.ApplicationModule
import com.ttawatchai.mvvm.injection.module.DatabaseModule
import com.ttawatchai.mvvm.injection.module.NetworkModule
import com.ttawatchai.mvvm.injection.module.NetworkModuleForMultiPart
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.feature.listdata.ui.ListViewModel
import com.ttawatchai.mvvm.feature.listdetail.DetailViewModel
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
    fun inject(viewmodel: ListViewModel)
    fun inject(viewmodel: DetailViewModel)
}
