package com.ttawatchai.mvvm.feature.listdata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ttawatchai.mvvm.R
import com.ttawatchai.mvvm.service.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val onClickListener: OnClickListener?,private val onClickFavListener: OnClickListener?) :
    PagedListAdapter<User, MainAdapter.DataViewHolder>(PostDiffCallback()) {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(onClickListener: OnClickListener?,onClickFavListener: OnClickListener?,user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                imageViewAvatar.setImageURI(user.avatar)
                if (user.fav!!) {
                    imageStar.setBackgroundResource(R.drawable.star__check)
                } else {
                    imageStar.setBackgroundResource(R.drawable.star)
                }
                lnImageStar.setOnClickListener {
                    onClickFavListener?.clicklistener!!(user)
                    if (user.fav!!) {
                        imageStar.setBackgroundResource(R.drawable.star__check)
                    } else {
                        imageStar.setBackgroundResource(R.drawable.star)
                    }
                }
                container.setOnClickListener {
                    onClickListener?.clicklistener!!(user)
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(onClickListener,onClickFavListener,it)
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.avatar == newItem.avatar && oldItem.email == newItem.email && oldItem.name == newItem.name
        }
    }

    class OnClickListener(val clicklistener: (model: User) -> Unit) {
        fun onClick(model: User) = clicklistener(model)
   }
}
