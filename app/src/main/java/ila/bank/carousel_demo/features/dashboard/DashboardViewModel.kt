package ila.bank.carousel_demo.features.dashboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ila.bank.carousel_demo.core.BaseViewModel
import ila.bank.carousel_demo.features.dashboard.models.CarouselItemData
import ila.bank.carousel_demo.features.dashboard.models.CoinItemData
import ila.bank.carousel_demo.features.dashboard.models.DashboardData
import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.domain.param.AllCoinsParams
import ila.bank.domain.param.CoinDetailsParams
import ila.bank.domain.responses.GetAllCoinResponse
import ila.bank.domain.usecases.AllCoinsUseCases
import ila.bank.domain.usecases.CoinDetailUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject
 


/**
 * Creating a DashboardVM with its owns viewData and its viewEvents
 * with its feature functionality
 * * */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val allCoinsUseCases: AllCoinsUseCases,
    private val coinDetailUseCases: CoinDetailUseCases
) : BaseViewModel() {

    internal val viewData = DashboardData()

    private val _events = MutableStateFlow<DashboardEvents>(DashboardEvents.Init)
    internal val events: StateFlow<DashboardEvents> = _events.asStateFlow()

    private fun updateEvents(newEvent: DashboardEvents) {
        _events.updateAndGet {
            newEvent
        }
        _events.updateAndGet {
            DashboardEvents.Init
        }
    }

    fun syncAllCoins(position: Int = 0) {
        viewData.showLoader = true
        viewModelScope.launch {


            when (val result = executeUseCase {
                allCoinsUseCases.buildUseCase(AllCoinsParams(position))
            }) {

                is ResultWrapper.Error -> {
                    viewData.showLoader = false
                    updateEvents(DashboardEvents.ShowToast(mapRateLimitError(result.error)))
                }

                is ResultWrapper.Success -> {
                    viewData.showLoader = false
                    bindToUIData(result.value)
                }
            }
        }
    }

    private fun bindToUIData(data: List<GetAllCoinResponse>) {
        val carouselItems = arrayListOf<CarouselItemData>()
        val coinsItems = arrayListOf<CoinItemData>()
        data.forEach {
            val imageUrl = it.image ?: ""
            carouselItems.add(CarouselItemData().apply {
                this.imageUrl = imageUrl
            })

            val overview = String.format(
                "%s\n#%s",
                it.name ?: "",
                it.symbol ?: ""
            )
            coinsItems.add(
                CoinItemData(
                    coinId = it.id ?: "",
                    image = imageUrl,
                    coinName = it.name ?: "",
                    overview = overview
                )
            )
        }
        updateEvents(DashboardEvents.OnSuccessData(carouselItems, coinsItems))
    }


    private fun syncCoinDetail() {
        viewData.showLoader = true
        viewModelScope.launch {
            when (val result = executeUseCase {
                coinDetailUseCases.buildUseCase(CoinDetailsParams("usd"))
            }) {
                is ResultWrapper.Error -> {
                    viewData.showLoader = false
                    updateEvents(DashboardEvents.ShowToast(mapRateLimitError(result.error)))
                }

                is ResultWrapper.Success -> {
                    viewData.showLoader = false
                    updateEvents(DashboardEvents.ShowToast(result.value))
                }
            }
        }
    }

    fun mapRateLimitError(apiError : ApiException)= if (apiError.resCode == 429) {
            "You've exceeded the Rate Limit. Try Proxy variant for Demo!"
        } else {
        apiError.errorMsg
        }

    fun onCoinsClick() {
        syncCoinDetail()
    }
}