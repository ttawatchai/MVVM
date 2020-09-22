package com.ttawatchai.mvvm.feature.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.databinding.FragmentDetailBinding
import com.ttawatchai.mvvm.databinding.FragmentLoginBinding
import com.ttawatchai.mvvm.feature.listdata.ui.ListFragment
import com.ttawatchai.mvvm.feature.listdetail.ui.DetailsFragment
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.getViewModel
import com.ttawatchai.mvvm.service.model.User


class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding


    private val viewModel by lazy {
        getViewModel<LoginViewModel>(
            LoginViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        setUi()
        return binding.root
    }

    private fun setUi() {
        if (prefsHelper.name.isNotEmpty()) {
            binding.usrusr.setText(prefsHelper.name)
        }
        binding.lnButton.setOnClickListener {
            prefsHelper.name = viewModel.user.get().toString()
            Log.d("name", prefsHelper.name)
            val nextFrag =
                ListFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .remove(this)
                .commit()
        }
    }

    companion object {
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
