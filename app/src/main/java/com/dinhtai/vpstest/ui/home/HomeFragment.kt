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
            Item(R.drawable.ic_1, "C??? phi???u"),
            Item(R.drawable.ic_2, "Ph??i sinh"),
            Item(R.drawable.ic_3, "Money Market"),
            Item(R.drawable.ic_4, "Tr??i phi???u"),
            Item(R.drawable.ic_5, "Thanh to??n"),
            Item(R.drawable.ic_6, "B???o hi???m"),
            Item(R.drawable.ic_7, "Giao d???ch ti???n"),
            Item(R.drawable.ic_8, "Vcookie"),
            Item(R.drawable.ic_9, "Qu???n l?? danh m???c"),
            Item(R.drawable.ic_10, "Ti???n g???i t???i NHTM")
        )

        val banners = mutableListOf(
            Item(R.drawable.banner_1, null),
            Item(R.drawable.banner_2, null),
            Item(R.drawable.banner_3, null),
            Item(R.drawable.banner_4, null),
        )

        val services = mutableListOf(
            Item(R.drawable.ic_12, "D???ch v??? Ch???ng kho??n"),
            Item(R.drawable.ic_13, "D???ch v??? SP t??i ch??nh"),
            Item(R.drawable.ic_14, "D???ch v??? Li??n k???t"),
            Item(R.drawable.ic_15, "D???ch v??? T??i kho???n")
        )

        vm.setBanners(banners)
        vm.setProducts(products)
        vm.setServices(services)
    }
}