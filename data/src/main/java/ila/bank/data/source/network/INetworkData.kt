package ila.bank.data.source.network

import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.models.response.CoinEntityDetail
import javax.inject.Singleton


@Singleton
interface INetworkData {
    /**
     * required the all coins from network data as per BL req/res models
     * */
    suspend fun fetchAllCoins(request: GetAllCoinRequest) : List<CoinEntity>
    /**
     * required the all coins from network data as per BL req/res models
     * */
    suspend fun fetchCoinDetail(request: GetCoinDetailRequest) : CoinEntityDetail

}