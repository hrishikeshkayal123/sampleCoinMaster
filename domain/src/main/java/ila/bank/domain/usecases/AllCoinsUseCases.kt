package ila.bank.domain.usecases

import ila.bank.data.core.ApiException
import ila.bank.data.core.ResultWrapper
import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.repositories.CoinDataRepository
import ila.bank.domain.core.UseCase
import ila.bank.domain.param.AllCoinsParams
import ila.bank.domain.responses.GetAllCoinResponse
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Build a AllCoinsUseCases without params so we have to set object no params
 * and mapping the api response into business logic model response for mobile apps
 * */

@Singleton
class AllCoinsUseCases @Inject constructor(private val coinDataRepository: CoinDataRepository) :
    UseCase<AllCoinsParams, List<CoinEntity>, List<GetAllCoinResponse>>() {


    override suspend fun buildUseCase(params: AllCoinsParams)
            : ResultWrapper<ApiException, List<GetAllCoinResponse>> {
        return execute {
            val request = GetAllCoinRequest(
                "usd",
                "market_cap_desc",
                10,
                params.position,
                false
            )
            coinDataRepository.getAllCoins(request)
        }
    }

    override fun mapResponse(response: List<CoinEntity>): List<GetAllCoinResponse> {
        val results = ArrayList<GetAllCoinResponse>(0)

        response.forEach { data ->
            results.add(
                GetAllCoinResponse(
                    data.id ?: "",
                    data.symbol ?: "",
                    data.name ?: "",
                    data.image ?: "",
                    data.current_price ?: 0.0,
                )
            )
        }
        return results
    }

}