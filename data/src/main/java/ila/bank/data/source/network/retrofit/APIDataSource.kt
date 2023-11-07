package ila.bank.data.source.network.retrofit

import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.models.response.CoinEntityDetail
import ila.bank.data.source.network.INetworkData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Execution of INetwork Data with retrofit mechanism
 */

@Singleton
class APIDataSource @Inject constructor(
    apiUrl: String,
    client: OkHttpClient
) : INetworkData {


    /**
     * Create an api service with retrofit builder
     */
    private val apiService: APIService = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(APIService::class.java)


    override suspend fun fetchAllCoins(request: GetAllCoinRequest): List<CoinEntity> {
        return apiService.getAllCoins(
            path = request.path,
            vs_currency = request.currency,
            order = request.order,
            perPage = request.perPage,
            page = request.page,
            sparkline = request.sparkline
        )
    }

    override suspend fun fetchCoinDetail(request: GetCoinDetailRequest): CoinEntityDetail {
        return apiService.getCoinDetail(path = request.path)
    }
}