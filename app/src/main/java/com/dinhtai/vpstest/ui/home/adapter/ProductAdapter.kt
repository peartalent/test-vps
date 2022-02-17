package com.dinhtai.vpstest.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dinhtai.vpstest.databinding.ItemProductBinding
import com.dinhtai.vpstest.data.Item
import java.util.*

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.BindingHolder>() {
    private val data = mutableListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.item = data[position]
    }

    override fun getItemCount() = data.size

    fun onMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    class BindingHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
}