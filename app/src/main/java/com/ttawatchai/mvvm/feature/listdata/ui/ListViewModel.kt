package com.ttawatchai.mvvm.feature.listdata.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.ttawatchai.mvvm.MainApplication
import com.ttawatchai.mvvm.service.repository.ApiRepository
import com.ttawatchai.mvvm.injection.database.dao.UserDao
import com.ttawatchai.mvvm.feature.listdata.adapter.MainAdapter
import com.ttawatchai.mvvm.feature.listdata.adapter.PageListMultiDropBoundaryCallback
import com.ttawatchai.mvvm.feature.listdata.model.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observable
import javax.inject.Inject

class ListViewModel : ViewModel() {

    init {
        MainApplication.dataComponent.inject(this)
    }

    @Inject
    lateinit var repository: ApiRepository

    @Inject
    lateinit var dao: UserDao
    private val compositeDisposable = CompositeDisposable()
    val pagedListMultiTask = MutableLiveData<PagedList<User>>()
    val countFav = dao.getCount()

    fun getMultiDrop(parentLifecycleOwner: LifecycleOwner) =
        compositeDisposable.add(fetchOrGetTask(parentLifecycleOwner).subscribe({
            pagedListMultiTask.value = it
        }, { it.printStackTrace() }))

    private fun fetchOrGetTask(parentLifecycleOwner: LifecycleOwner): Observable<PagedList<User>> {
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
                    parentLifecycleOwner, repository, dao
                )
            )
            .buildObservable()
    }

    fun updatePostList(adapter: MainAdapter, newPostList: PagedList<User>) {
        adapter.submitList(newPostList)
    }

    fun saveToDb(user: User) {
        dao.updatDevice(user)
    }

}