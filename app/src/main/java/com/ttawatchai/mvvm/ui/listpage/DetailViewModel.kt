package com.ttawatchai.mvvm.ui.listpage

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.data.repository.MainRepository
import com.ttawatchai.mvvm.database.dao.UserDao
import com.ttawatchai.mvvm.ui.list.adapter.MainAdapter
import com.ttawatchai.mvvm.ui.list.adapter.PageListMultiDropBoundaryCallback
import com.ttawatchai.mvvm.ui.list.model.User
import com.ttawatchai.networklibrary.model.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Inject

class DetailViewModel : ViewModel() {

    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var dao: UserDao
    val user = ObservableField<User>()
    val email = ObservableField<String>("test@email.com")
    val isFav = ObservableField<Boolean>(false)


}