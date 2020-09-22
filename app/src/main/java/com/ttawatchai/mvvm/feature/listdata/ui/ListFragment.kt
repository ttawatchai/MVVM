package com.ttawatchai.mvvm.feature.listdata.ui

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.databinding.FragmentListBinding
import com.ttawatchai.mvvm.feature.listdata.adapter.MainAdapter
import com.ttawatchai.mvvm.feature.listdetail.ui.DetailsFragment
import com.ttawatchai.mvvm.feature.login.LoginFragment
import com.ttawatchai.mvvm.injection.base.BaseFragment
import com.ttawatchai.mvvm.injection.base.getViewModel
import kotlinx.android.synthetic.main.content_scrolling.view.*
import kotlinx.android.synthetic.main.item_app_bar_main.view.*


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

    private fun initCollapToolbar(size: Int) {
        val collapsingToolbarLayout: CollapsingToolbarLayout = binding.collapsingToolbar
        val carSize =
            getString(R.string.title_have_car) + " " + size + " คน"
        collapsingToolbarLayout.isTitleEnabled = true
        collapsingToolbarLayout.title = carSize
        collapsingToolbarLayout.setCollapsedTitleTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        collapsingToolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(
                context!!,
                R.color.transparent
            )
        ) // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar)
        collapsingToolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(
                context!!,
                R.color.transparent
            )
        )
        collapsingToolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                context!!,
                R.color.colorWhite
            )
        )
        binding.itemBar.tvCarHave.text = carSize
        binding.itemBar.tvCarHave.setTypeface(
            binding.itemBar.tvCarHave.typeface,
            Typeface.BOLD
        )
    }

    private fun subscribeToModel() {
        binding.toolbar.flExit.setOnClickListener {
            openAlertDialog()
        }
        viewModel.countFav.observe(this, Observer {
            initCollapToolbar(it)
        })
        val adapter = MainAdapter(
            MainAdapter.OnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("Object", it)
                val nextFrag =
                    DetailsFragment()
                nextFrag.arguments = bundle
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit()
            },
            MainAdapter.OnClickListener {
                it.apply {
                    it.fav = !fav
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

    private fun openAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you sure you want to Exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                viewModel.dao.deleteAll()
                prefsHelper.clearPref()
                val nextFrag =
                    LoginFragment()
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .remove(this)
                    .commit()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}
