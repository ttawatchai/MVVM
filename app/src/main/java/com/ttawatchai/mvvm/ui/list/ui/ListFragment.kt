package com.ttawatchai.mvvm.ui.list.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.databinding.FragmentListBinding
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.getViewModel
import com.ttawatchai.mvvm.ui.list.adapter.MainAdapter
import com.ttawatchai.mvvm.ui.list.model.User
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: MainAdapter


    private val viewModel by lazy {
        getViewModel<ListViewModel>(ListViewModel::class.java)
    }

    companion object {
        fun newInstance(): ListFragment {
            val args = Bundle()
            val fragment = ListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        setupUI()
        subscribeToModel()
        return binding.root
    }

    private fun setupUI() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//        adapter = MainAdapter(arrayListOf())
//        binding.recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                binding.recyclerView.context,
//                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
//        binding.recyclerView.adapter = adapter
    }



    private fun subscribeToModel() {
        val adapter = MainAdapter()
        binding.recyclerView.layoutManager =  LinearLayoutManager(context)

        viewModel.getMultiDrop()
        viewModel.pagedListMultiTask.observe(this, Observer {
            if (it.size != 0) {
                Log.d("PageList_add_size", it.size.toString())
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                if (adapter.itemCount == 0) {
                    adapter.submitList(it)
                } else {
                    viewModel.updatePostList(adapter, it)
                }
            }
        })

        binding.recyclerView.adapter = adapter

//        newsArticleViewModel.getUsers().observe(this, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        recyclerView.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
//                        resource.data?.let { users -> retrieveList(users) }
//                    }
//                    Status.ERROR -> {
//                        recyclerView.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
//                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//                        progressBar.visibility = View.VISIBLE
//                        recyclerView.visibility = View.GONE
//                    }
//                    else ->
//                        Toast . makeText (activity, "Fail", Toast.LENGTH_LONG).show()
//                }
//            }
//        })
    }
}
