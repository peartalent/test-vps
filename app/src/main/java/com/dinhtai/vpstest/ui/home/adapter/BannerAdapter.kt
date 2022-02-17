package com.dinhtai.vpstest.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dinhtai.vpstest.data.Item
import com.dinhtai.vpstest.databinding.ItemBannerBinding

class BannerAdapter() : RecyclerView.Adapter<BannerAdapter.BindingHolder>() {
    private val data = mutableListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.item = data[position]
    }

    override fun getItemCount() = data.size

    class BindingHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root)
}