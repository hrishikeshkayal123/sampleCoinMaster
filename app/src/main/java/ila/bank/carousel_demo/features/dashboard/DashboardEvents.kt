package ila.bank.carousel_demo.features.dashboard

import ila.bank.carousel_demo.features.dashboard.models.CarouselItemData
import ila.bank.carousel_demo.features.dashboard.models.CoinItemData


/**
 * DashboardEvents is set of sealed events which will be observe form VM
 * */
sealed class DashboardEvents {

    object Init : DashboardEvents()
    data class OnSuccessData(val carouselItems: List<CarouselItemData>, val coinsItems : List<CoinItemData>) : DashboardEvents()
    data class ShowToast(val message: String?) : DashboardEvents()
}