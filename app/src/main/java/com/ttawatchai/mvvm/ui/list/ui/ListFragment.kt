package com.ttawatchai.mvvm.ui.list.ui

import android.R.attr.fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.databinding.FragmentListBinding
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.getViewModel
import com.ttawatchai.mvvm.ui.list.adapter.MainAdapter
import com.ttawatchai.mvvm.ui.listpage.DetailsFragment
import kotlinx.android.synthetic.main.content_scrolling.view.*


class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding


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
        subscribeToModel()
        return binding.root
    }

    private fun subscribeToModel() {
        val adapter = MainAdapter(
            MainAdapter.OnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("Object", it)
                val nextFrag = DetailsFragment()
                nextFrag.arguments = bundle
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit()
            },
            MainAdapter.OnClickListener {
                it.apply {
                    it.fav = !fav!!
                }.also { user ->
                    viewModel.saveToDb(user)
                }
            })
        binding.root.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getMultiDrop(this)
        viewModel.pagedListMultiTask.observe(this, Observer {
            if (it.size != 0) {
                Log.d("PageList_add_size", it.size.toString())
                binding.root.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                if (adapter.itemCount == 0) {
                    adapter.submitList(it)
                } else {
                    viewModel.updatePostList(adapter, it)
                }
            }
        })
        binding.root.recyclerView.adapter = adapter
    }
}
