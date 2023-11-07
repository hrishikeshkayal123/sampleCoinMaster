package ila.bank.data.source.proxy

import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.models.response.CoinEntityDetail
import ila.bank.data.source.network.INetworkData
import ila.bank.data.source.proxy.coins.FetchAllCoins
import ila.bank.data.source.proxy.coins.FetchCoinsDetail
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Proxy Data mapping the proxy logic of apis logic
 */
@Singleton
class ProxyDataSource @Inject constructor(): INetworkData {

    private val fetchAllCoins by lazy {
        FetchAllCoins()
    }
    private val fetchCoinDetails by lazy {
        FetchCoinsDetail()
    }

    override suspend fun fetchAllCoins(request: GetAllCoinRequest): List<CoinEntity> {
        return fetchAllCoins.execute(request)
    }

    override suspend fun fetchCoinDetail(request: GetCoinDetailRequest): CoinEntityDetail {
        return fetchCoinDetails.execute(request)
    }
}