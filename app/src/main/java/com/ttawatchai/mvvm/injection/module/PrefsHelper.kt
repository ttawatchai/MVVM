package com.ttawatchai.mvvm.injection.module

import android.content.Context
import android.content.SharedPreferences

class PrefsHelper(context: Context) {
    private val mSharedPreferences: SharedPreferences =
        context.getSharedPreferences(PrefsHelper::class.java.name, Context.MODE_PRIVATE)


    var name: String
        get() = mSharedPreferences.getString("name", "")!!
        set(data) {
            mSharedPreferences.edit().putString("name", data).apply()
        }

    fun clearPref() {
        mSharedPreferences.edit().clear().apply()
    }

}
