package com.dinhtai.vpstest.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinhtai.vpstest.R
import com.dinhtai.vpstest.data.Item
import com.dinhtai.vpstest.ui.home.adapter.BannerAdapter
import com.dinhtai.vpstest.ui.home.adapter.ProductAdapter
import com.dinhtai.vpstest.ui.home.adapter.ServiceAdapter
import com.dinhtai.vpstest.ui.home.adapter.ShortProductAdapter


@BindingAdapter("android:src")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}

@BindingAdapter("setShortProducts")
fun setShortProducts(
    recyclerView: RecyclerView,
    data: MutableList<Item>?
) {
    if (data.isNullOrEmpty()) return
    if (data.size >= 8) {
        val shortData = mutableListOf<Item>()
        shortData.addAll((data.subList(0, 7)))
        shortData.add(Item(R.drawable.ic_11, "Xem thêm", true))
        (recyclerView.adapter as ShortProductAdapter).setData(shortData)
    } else {
        (recyclerView.adapter as ShortProductAdapter).setData(data)
    }
}

@BindingAdapter("setProducts")
fun setProducts(
    recyclerView: RecyclerView,
    data: MutableList<Item>?
) {
    print(data)
    if (data.isNullOrEmpty()) return
    (recyclerView.adapter as ProductAdapter).setData(data)
}


@BindingAdapter("setBanners")
fun setBanners(
    recyclerView: RecyclerView,
    data: MutableList<Item>?
) {
    if (data.isNullOrEmpty()) return
    (recyclerView.adapter as BannerAdapter).setData(data)
}

@BindingAdapter("setServices")
fun setServices(
    recyclerView: RecyclerView,
    data: MutableList<Item>?
) {
    if (data.isNullOrEmpty()) return
    (recyclerView.adapter as ServiceAdapter).setData(data)
}
