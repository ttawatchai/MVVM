package com.ttawatchai.mvvm.feature.listdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.databinding.FragmentDetailBinding
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.getViewModel
import com.ttawatchai.mvvm.service.model.User


class DetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding


    private val viewModel by lazy {
        getViewModel<DetailViewModel>(
            DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val bundle = this.arguments
        if (bundle != null) {
            val user = bundle.getParcelable<User>("Object")
            viewModel.user.set(user)
        }
        setUi()
        return binding.root
    }

    private fun setUi() {
        binding.tvName.text = viewModel.user.get()?.name
        binding.tvEmail.text = viewModel.user.get()?.email
        binding.tvId.text = "#${viewModel.user.get()?.id}"
        binding.imageViewAvatar.setImageURI(viewModel.user.get()?.avatar)
        if (viewModel.user.get()?.fav!!) {
            binding.imageStar.setBackgroundResource(R.drawable.star__check)
        } else {
            binding.imageStar.setBackgroundResource(R.drawable.star)
        }
        binding.lnImageStar.setOnClickListener {
            if (viewModel.user.get()?.fav!!) {
                binding.imageStar.setBackgroundResource(R.drawable.star)
            } else {
                binding.imageStar.setBackgroundResource(R.drawable.star__check)
            }
            viewModel.updateDatabase()

        }
    }
}
