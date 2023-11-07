package ila.bank.domain.usecases

import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntityDetail
import ila.bank.data.repositories.CoinDataRepository
import ila.bank.domain.core.UseCase
import ila.bank.domain.param.CoinDetailsParams
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Build a CoinDetailUseCases with params CoinDetailsParams and we create this object from consumer
 * of use-cases and mapping the api response into business logic model response for mobile apps
 * */
@Singleton
class CoinDetailUseCases @Inject constructor(private val coinDataRepository: CoinDataRepository) :
    UseCase<CoinDetailsParams, CoinEntityDetail, String>() {


    override suspend fun buildUseCase(params: CoinDetailsParams)
            : ResultWrapper<ApiException, String> {
        return execute {
            val request = GetCoinDetailRequest(params.currency)
            coinDataRepository.getCoinDetail(request)
        }
    }

    override fun mapResponse(response: CoinEntityDetail): String {
        return response.description?.en ?: ""
    }

}