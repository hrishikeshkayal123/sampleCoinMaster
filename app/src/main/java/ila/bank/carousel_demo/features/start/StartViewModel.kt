package ila.bank.carousel_demo.features.start

import android.os.Handler
import dagger.hilt.android.lifecycle.HiltViewModel
import ila.bank.carousel_demo.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject




/**
 * Creating a StartVM with its owns viewData and its viewEvents
 * with its feature functionality
 * * */

@HiltViewModel
class StartViewModel
@Inject constructor(
    private val handler: Handler,
) : BaseViewModel() {

    internal val viewData = StartData()

    private val _events = MutableStateFlow<SplashEvents>(SplashEvents.Init)
    internal val events: StateFlow<SplashEvents> = _events.asStateFlow()


    init {
         syncSplashWork()
    }

    private fun syncSplashWork() {
        handler.postDelayed({
            viewData.showProceed = true
        }, 2000)
    }

    fun onProceedClick() {
        _events.update {
            SplashEvents.NavigateToDashBoard
        }
    }

}