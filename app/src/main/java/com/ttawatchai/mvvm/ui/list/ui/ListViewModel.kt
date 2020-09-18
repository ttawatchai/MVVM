package com.ttawatchai.mvvm.ui.list.ui

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

class ListViewModel : ViewModel() {

    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var repository: MainRepository

    @Inject
    lateinit var dao: UserDao
    private val compositeDisposable = CompositeDisposable()
    val pagedListMultiTask = MutableLiveData<PagedList<User>>()
    val pageListMultiDropBoundaryCallback =
        PageListMultiDropBoundaryCallback(repository,dao)


    fun getUsers() = liveData(Dispatchers.IO) {
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

    fun getMultiDrop() =
        compositeDisposable.add(fetchOrGetTask().subscribe({
            pagedListMultiTask.value = it
        }, { it.printStackTrace() }))

//        multiDropHistorySize.value = getSizeTask()


    private fun fetchOrGetTask(): Observable<PagedList<User>> {
        return RxPagedListBuilder(
            dao.getDataSourcefactory(), PagedList.Config.Builder()
                .setPageSize(5)
                .setPrefetchDistance(5)
                .setInitialLoadSizeHint(20)
                .setEnablePlaceholders(true)
                .build()
        )
            .setBoundaryCallback(
                PageListMultiDropBoundaryCallback(
                    repository, dao

                )
            )
            .buildObservable()
    }

    fun disposable() = pageListMultiDropBoundaryCallback.disposable

    fun updatePostList(adapter: MainAdapter, newPostList: PagedList<User>) {
        adapter.submitList(newPostList)
    }

}