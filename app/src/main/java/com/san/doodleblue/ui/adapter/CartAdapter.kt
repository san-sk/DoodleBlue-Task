package com.san.doodleblue.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.san.doodleblue.core.data.entity.MenuItem
import com.san.doodleblue.databinding.ItemMenuBinding

class CartAdapter(val callaBack: (item: MenuItem) -> Unit) :
    ListAdapter<MenuItem, RecyclerView.ViewHolder>(MenuItemDiffCallback()) {

    class MenuItemDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(
            oldItem: MenuItem,
            newItem: MenuItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MenuItem,
            newItem: MenuItem
        ): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuItemHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MenuItemHolder).onBind(getItem(position))
    }


    inner class MenuItemHolder(var binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: MenuItem) {
            with(binding) {
                binding.cvNew.setCallBack {
                    Log.i("RVT", item.toString())
                    callaBack.invoke(item.copy(count = it))
                    return@setCallBack
                }
                tvTitle.text = item.name
                tvDescription.text = item.description
                tvAmount.text = item.price.toString()
                binding.cvNew.counterValue = item.count
            }
        }
    }

}