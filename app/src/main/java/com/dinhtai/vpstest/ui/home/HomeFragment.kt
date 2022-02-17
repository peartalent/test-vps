package com.dinhtai.vpstest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dinhtai.vpstest.R
import com.dinhtai.vpstest.data.Item
import com.dinhtai.vpstest.databinding.FragmentHomeBinding
import com.dinhtai.vpstest.ui.home.adapter.BannerAdapter
import com.dinhtai.vpstest.ui.home.adapter.ServiceAdapter
import com.dinhtai.vpstest.ui.home.adapter.ShortProductAdapter
import com.dinhtai.vpstest.ui.home.viewmodel.HomeViewModel
import com.dinhtai.vpstest.ui.bottomsheet.DialogBottomSheet

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var shortProductAdapter: ShortProductAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var serviceAdapter: ServiceAdapter
    private val vm = HomeViewModel.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(inflater, container, false).let {
            binding = it
            binding.vm = vm
            it.lifecycleOwner = viewLifecycleOwner
            it.root
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortProductAdapter = ShortProductAdapter(::onMoreClick)
        bannerAdapter = BannerAdapter()
        serviceAdapter = ServiceAdapter()

        binding.recyclerProduct.setNestedScrollingEnabled(false);
        binding.recyclerProduct.adapter = shortProductAdapter
        binding.recyclerFunction.adapter = bannerAdapter
        binding.recyclerService.adapter = serviceAdapter
        setData()
    }

    private fun onMoreClick(item: Item) {
        if (item.isMoreItem) {
            DialogBottomSheet(vm).show(parentFragmentManager, null)
        }
    }

    private fun setData() {
        val products = mutableListOf(
            Item(R.drawable.ic_1, "Cổ phiếu"),
            Item(R.drawable.ic_2, "Phái sinh"),
            Item(R.drawable.ic_3, "Money Market"),
            Item(R.drawable.ic_4, "Trái phiếu"),
            Item(R.drawable.ic_5, "Thanh toán"),
            Item(R.drawable.ic_6, "Bảo hiểm"),
            Item(R.drawable.ic_7, "Giao dịch tiền"),
            Item(R.drawable.ic_8, "Vcookie"),
            Item(R.drawable.ic_9, "Quản lý danh mục"),
            Item(R.drawable.ic_10, "Tiền gửi tại NHTM")
        )

        val banners = mutableListOf(
            Item(R.drawable.banner_1, null),
            Item(R.drawable.banner_2, null),
            Item(R.drawable.banner_3, null),
            Item(R.drawable.banner_4, null),
        )

        val services = mutableListOf(
            Item(R.drawable.ic_12, "Dịch vụ Chứng khoán"),
            Item(R.drawable.ic_13, "Dịch vụ SP tài chính"),
            Item(R.drawable.ic_14, "Dịch vụ Liên kết"),
            Item(R.drawable.ic_15, "Dịch vụ Tài khoản")
        )

        vm.setBanners(banners)
        vm.setProducts(products)
        vm.setServices(services)
    }
}