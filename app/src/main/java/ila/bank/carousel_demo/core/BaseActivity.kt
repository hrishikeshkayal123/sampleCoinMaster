package ila.bank.carousel_demo.core

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding



/**
 * Creating an base activity for UI activities with some boilerplate
 * functionality
 */
abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {


    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun onInitialized(mBinding: V)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val mBinding: V = DataBindingUtil.setContentView(this@BaseActivity, getLayoutResId())
            mBinding.lifecycleOwner = this
            onInitialized(mBinding)

        }
    }

    protected fun initEvent() {
        Log.d(this::class.simpleName, "initEvent collected")
    }


}