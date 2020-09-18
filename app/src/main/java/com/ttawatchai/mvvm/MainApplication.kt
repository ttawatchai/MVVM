package com.ttawatchai.mvvm

import android.app.Application
import com.ttawatchai.mvvm.injection.ApplicationComponent
import com.facebook.drawee.backends.pipeline.Fresco
import com.ttawatchai.mvvm.injection.DaggerApplicationComponent
import com.ttawatchai.mvvm.injection.module.ApplicationModule


class MainApplication : Application() {

    companion object {
        lateinit var dataComponent: ApplicationComponent
        var isApplicationVisible = true
    }

    override fun onCreate() {
        super.onCreate()


        Fresco.initialize(this)

        dataComponent =
            DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        dataComponent.inject(this)
    }

}
