package ila.bank.carousel_demo.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import ila.bank.carousel_demo.R
import ila.bank.carousel_demo.features.dashboard.DashboardViewModel



/**
 * Binding adapters for UI Listeners
 */
object UIBindingAdapters {


    @JvmStatic
    @BindingAdapter("bindImageUrl")
    fun setImageUrl(imageView: AppCompatImageView, imageUrl: String) {

        val context: Context = imageView.context ?: return
        val diskCache = DiskCache.Builder()
            .directory(context.cacheDir.resolve("image_cache"))
            .build()
        val memoryCache = MemoryCache.Builder(context)
            .build()
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .placeholder(R.drawable.loader)
            .error(R.drawable.error)
            .target(imageView)
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .memoryCache(memoryCache)
            .diskCache(diskCache)
            .build()

        imageLoader.enqueue(request)
    }

}