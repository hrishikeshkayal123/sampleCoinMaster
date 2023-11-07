package ila.bank.carousel_demo.features.start

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ila.bank.carousel_demo.R
import ila.bank.carousel_demo.core.BaseActivity
import ila.bank.carousel_demo.databinding.ActivityStartBinding
import ila.bank.carousel_demo.features.dashboard.DashboardActivity
import ila.bank.carousel_demo.utils.navigateToActivity



/**
 * StartActivity is UI file with Hilt @AndroidEntryPoint to inject the necessary objects
 * */
@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>() {

    private val mViewModel: StartViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_start

    override fun onInitialized(mBinding: ActivityStartBinding) {

        mBinding.viewData = mViewModel.viewData
        mBinding.viewModel = mViewModel

        lifecycleScope.launchWhenCreated {
            mViewModel.events.collect { events ->
                when (events) {
                    SplashEvents.Init -> initEvent()
                    SplashEvents.NavigateToDashBoard -> {
                        navigateToActivity(DashboardActivity::class.java, true)
                    }
                }

            }
        }

    }

}