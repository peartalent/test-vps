package com.dinhtai.vpstest.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dinhtai.vpstest.databinding.ItemProductBinding
import com.dinhtai.vpstest.data.Item

class ShortProductAdapter(private val onClick: (item: Item) -> Unit) :
    RecyclerView.Adapter<ShortProductAdapter.BindingHolder>() {
    private val data = mutableListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.item = data[position]
        holder.binding.root.setOnClickListener {
            onClick(data[position])
        }
    }

    override fun getItemCount() = data.size

    class BindingHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
}