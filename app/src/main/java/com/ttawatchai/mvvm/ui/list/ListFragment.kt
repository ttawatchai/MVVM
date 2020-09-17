package com.ttawatchai.mvvm.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttawatchai.mvvm.ui.base.BaseFragment
import com.ttawatchai.mvvm.ui.base.getViewModel


class ListFragment : BaseFragment() {

//    private lateinit var binding: FragmentSettingBinding
    private lateinit var viewModel: ListViewModel

    companion object {
        fun newInstance(): ListFragment {
            val args = Bundle()
            val fragment = ListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ListViewModel.Factory()
        viewModel = getViewModel(ListViewModel::class.java, factory)
        subscribeToModel()
    }

    private fun subscribeToModel() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
