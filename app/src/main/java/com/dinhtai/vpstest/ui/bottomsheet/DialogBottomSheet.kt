package com.dinhtai.vpstest.ui.bottomsheet

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dinhtai.vpstest.R
import com.dinhtai.vpstest.data.Item
import com.dinhtai.vpstest.databinding.BottomSheetBinding
import com.dinhtai.vpstest.ui.home.adapter.ProductAdapter
import com.dinhtai.vpstest.ui.home.viewmodel.HomeViewModel
import com.dinhtai.vpstest.utils.ItemTouchHelperCallback
import com.dinhtai.vpstest.utils.ItemTouchListenner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogBottomSheet(private val vm: HomeViewModel) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetBinding
    private lateinit var productAdapter: ProductAdapter

    private val mData = mutableListOf<Item>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = DataBindingUtil
            .inflate<BottomSheetBinding>(inflater, R.layout.bottom_sheet, container, false)
            .apply { binding = this }.root
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.setEditing(false)
        productAdapter = ProductAdapter()
        binding.recyclerProduct.adapter = productAdapter
        binding.recyclerProduct.setNestedScrollingEnabled(false);
        vm.products.value?.let {
            mData.clear()
            mData.addAll(it)
        }
        binding.data = mData
        dialog?.let {
            it.setOnShowListener {
                val bottomSheet =
                    (it as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                val layoutParams = bottomSheet.layoutParams
                val displayMetrics = DisplayMetrics()
                activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                layoutParams.height = displayMetrics.heightPixels * 2 / 3
//                bottomSheet.layoutParams = layoutParams
//                behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            }
        }

        binding.imgCancel.setOnClickListener { dismiss() }
        binding.txtFix.setOnClickListener {
            if (vm.isEditing.value == true) {
                vm.setProducts(mData)
                vm.setEditing(false)
                mData.clear()
                mData.addAll(productAdapter.getData())
                binding.txtFix.text = view.resources.getText(R.string.fix)
                binding.txtTool.visibility = View.GONE
                dismiss()
            } else {
                binding.txtFix.text = view.resources.getText(R.string.save)
                vm.setEditing(true)
                binding.txtTool.visibility = View.VISIBLE
            }
            addItemTouchCallback(binding.recyclerProduct, vm.isEditing.value)
        }
    }

    private fun addItemTouchCallback(recyclerView: RecyclerView, isEdit: Boolean?) {
        val callback: ItemTouchHelper.Callback =
            ItemTouchHelperCallback(object : ItemTouchListenner {
                override fun onMove(oldPosition: Int, newPosition: Int) {
                    productAdapter.onMove(oldPosition, newPosition)
                    mData.clear()
                    mData.addAll(productAdapter.getData())
                }

                override fun swipe(position: Int, direction: Int) {
                }
            })
        val itemTouchHelper = ItemTouchHelper(callback)
        if (isEdit == true) {
            itemTouchHelper.attachToRecyclerView(recyclerView)
        } else {
            itemTouchHelper.attachToRecyclerView(null)
        }
    }
}