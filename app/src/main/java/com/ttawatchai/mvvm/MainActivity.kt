package com.ttawatchai.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ttawatchai.mvvm.feature.listdata.ui.ListFragment
import com.ttawatchai.mvvm.feature.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame_content,
                LoginFragment.newInstance()
            )
            .commit()
    }
}