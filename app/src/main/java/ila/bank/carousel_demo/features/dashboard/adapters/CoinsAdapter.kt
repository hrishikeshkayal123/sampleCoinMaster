package ila.bank.carousel_demo.features.dashboard.adapters

import ila.bank.carousel_demo.R
import ila.bank.carousel_demo.core.BaseAdapter
import ila.bank.carousel_demo.databinding.ItemCoinsBinding
import ila.bank.carousel_demo.features.dashboard.DashboardViewModel
import ila.bank.carousel_demo.features.dashboard.models.CoinItemData

 

/**
 * CoinsAdapter is adapter to implement the recyclerview data
 * */
class CoinsAdapter constructor(private val mViewModel: DashboardViewModel) :
    BaseAdapter<CoinItemData, ItemCoinsBinding>() {

    override fun getLayoutResId(): Int = R.layout.item_coins

    override fun onBindItem(data: CoinItemData, rvItemBinding: ItemCoinsBinding) {
        rvItemBinding.viewModel = mViewModel
        rvItemBinding.itemData = data
    }
}