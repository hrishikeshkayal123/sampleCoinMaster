package ila.bank.carousel_demo.features.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager.widget.ViewPager
import dagger.hilt.android.AndroidEntryPoint
import ila.bank.carousel_demo.R
import ila.bank.carousel_demo.core.BaseActivity
import ila.bank.carousel_demo.databinding.ActivityDashboardBinding
import ila.bank.carousel_demo.features.dashboard.adapters.CarouselPagerAdapter
import ila.bank.carousel_demo.features.dashboard.adapters.CoinsAdapter
import ila.bank.carousel_demo.features.dashboard.models.CoinItemData
import ila.bank.carousel_demo.utils.showToast
import kotlinx.coroutines.launch

 

/**
 * DashboardActivity is UI file with Hilt @AndroidEntryPoint to inject the necessary objects
 * */
@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private lateinit var carouselPagerAdapter: CarouselPagerAdapter
    private lateinit var coinsAdapter: CoinsAdapter

    private val mViewModel: DashboardViewModel by viewModels()
    private val coinItems = arrayListOf<CoinItemData>()

    override fun getLayoutResId(): Int = R.layout.activity_dashboard

    override fun onInitialized(mBinding: ActivityDashboardBinding) {
        mBinding.viewModel = mViewModel
        mBinding.viewData = mViewModel.viewData

        initCarouselView(mBinding)
        initRecyclerView(mBinding)
        lifecycleScope.launchWhenCreated {
            mViewModel.events.collect { events ->
                when (events) {
                    is DashboardEvents.Init -> initEvent()
                    is DashboardEvents.OnSuccessData -> {
                        carouselPagerAdapter.setCarouselItems(events.carouselItems)
                        coinsAdapter.setItems(events.coinsItems)
                        coinItems.addAll(events.coinsItems)
                    }

                    is DashboardEvents.ShowToast -> showToast(events.message)

                }

            }
        }
        lifecycleScope.launch {
            mViewModel.syncAllCoins()
        }


    }

    private fun initRecyclerView(mBinding: ActivityDashboardBinding) {

        coinsAdapter = CoinsAdapter(mViewModel)

        mBinding.rvCoins.run {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = coinsAdapter
        }
    }

    @SuppressLint("DiscouragedApi")
    private fun initCarouselView(mBinding: ActivityDashboardBinding) {
        carouselPagerAdapter = CarouselPagerAdapter()
        mBinding.run {
            viewPager.run {
                adapter = carouselPagerAdapter
                clipToPadding = false
                setPadding(30, 30, 30, 30)
                pageMargin = 30
            }
            tabLayout.setupWithViewPager(mBinding.viewPager, true)
            edtSearch.setOnClickListener {
                motionLayout.transitionToEnd()
                motionLayout.requestFocus()
            }
            edtSearch.setOnFocusChangeListener { _, hasFocus ->
                run {
                    if (hasFocus) {
                        motionLayout.transitionToEnd()
                    }
                }
            }

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    mViewModel.syncAllCoins(position)
                }
            })

            edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(inputText: String?): Boolean {
                    searchItems(inputText)
                    return false
                }
            })

            val closeButtonId = resources.getIdentifier("android:id/search_close_btn", null, null)
            val closeButtonImage = edtSearch.findViewById(closeButtonId) as ImageView

            closeButtonImage.setOnClickListener {
                edtSearch.setQuery("",true)
                closeButtonImage.visibility = View.GONE
                edtSearch.clearFocus()
                motionLayout.transitionToStart()
            }

        }

    }

    private fun searchItems(inputText: String?) {
        if (inputText.isNullOrEmpty()) {
            coinsAdapter.setItems(coinItems)
            return
        }
        val filterData = arrayListOf<CoinItemData>()
        coinItems.forEach { i ->
            if (i.coinName.lowercase().startsWith(inputText.lowercase())) {
                filterData.add(i)
            }
        }
        coinsAdapter.setItems(filterData)
    }
}

