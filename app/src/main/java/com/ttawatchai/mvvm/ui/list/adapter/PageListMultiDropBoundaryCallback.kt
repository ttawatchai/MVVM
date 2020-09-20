package com.ttawatchai.mvvm.ui.list.adapter

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.lifecycle.observe
import androidx.paging.PagedList
import com.ttawatchai.mvvm.data.repository.MainRepository
import com.ttawatchai.mvvm.database.dao.UserDao
import com.ttawatchai.mvvm.ui.list.model.User
import com.ttawatchai.networklibrary.model.Resource
import com.ttawatchai.networklibrary.model.Status
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers

class PageListMultiDropBoundaryCallback(
    private val parentLifecycleOwner: LifecycleOwner,
    private val repository: MainRepository,
    private val dao: UserDao
) : PagedList.BoundaryCallback<User>() {

    override fun onZeroItemsLoaded() {
        Log.d("paglist","onZeroItemsLoaded")
        fetchUsers()
    }

    override fun onItemAtFrontLoaded(itemAtFront: User) {
        super.onItemAtFrontLoaded(itemAtFront)

    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {

    }


    private fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUsers()))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    msg = exception.message ?: "Error Occurred!",
                    error = ""
                )
            )
        }
    }

    private fun fetchUsers() {
        getUsers().observe(parentLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("paglist","onZeroItemsLoaded")
                        resource.data?.let { it1 -> dao.insert(it1) }
                    }
                    Status.ERROR -> {
                        Log.e("ERROR", "api_error${resource.message}")
                    }
                    else ->
                        Log.e("ERROR", "api error not return")
                }
            }
        })
    }
}