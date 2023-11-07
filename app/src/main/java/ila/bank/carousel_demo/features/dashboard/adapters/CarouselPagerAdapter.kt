package ila.bank.carousel_demo.features.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import ila.bank.carousel_demo.R
import ila.bank.carousel_demo.databinding.ItemCarouselBinding
import ila.bank.carousel_demo.features.dashboard.models.CarouselItemData


/**
 * CarouselPagerAdapter is adapter to implement the recyclerview data
 * */
class CarouselPagerAdapter : PagerAdapter() {

    private var carouselItems: List<CarouselItemData> = arrayListOf()

    override fun getCount(): Int {
        return carouselItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as CardView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ItemCarouselBinding>(
            LayoutInflater.from(container.context),
            R.layout.item_carousel,
            container,
            false
        )

        binding.itemData = carouselItems[position]
        container.addView(binding.root)
        return binding.root
    }


    fun setCarouselItems(carouselItems: List<CarouselItemData>) {
        this.carouselItems = carouselItems
        notifyDataSetChanged()
    }

}