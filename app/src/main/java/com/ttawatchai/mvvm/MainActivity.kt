package com.ttawatchai.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ttawatchai.mvvm.ui.list.ui.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame_content,
                ListFragment.newInstance()
            )
            .commit()
    }
}