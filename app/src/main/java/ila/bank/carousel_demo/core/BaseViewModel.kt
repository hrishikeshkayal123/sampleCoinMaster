package ila.bank.carousel_demo.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import kotlinx.coroutines.withContext
import javax.inject.Inject



/**
 * Creating an base viewModels for features VMs with some boilerplate
 * functionality
 */
@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {


    protected suspend fun <R> executeUseCase(useCase: suspend () -> ResultWrapper<ApiException, R>) =
        withContext(viewModelScope.coroutineContext) {
            useCase.invoke()
        }


}