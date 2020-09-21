package com.ttawatchai.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ttawatchai.mvvm.feature.listdata.ui.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame_content,
                ListFragment.newInstance()
            )
            .commit()
    }
}