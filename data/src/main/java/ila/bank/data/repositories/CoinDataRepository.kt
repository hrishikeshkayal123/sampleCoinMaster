package ila.bank.data.repositories

import ila.bank.data.core.ResultWrapper
import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.models.response.CoinEntityDetail
import ila.bank.data.source.DataSourceMode
import ila.bank.data.source.network.INetworkData
import ila.bank.data.source.proxy.ProxyDataSource
import javax.inject.Inject
import javax.inject.Singleton




/***
 * Created a repo layer to execute the data from network call
 * as well from proxy call by using data  source mode
 * defaultDataSourceMode will execute all the apis as per this mode
 * also we can override the defaultDataSourceMode for specific api
 * with help of dataSourceMode prams from api repo function
 * */
@Singleton
class CoinDataRepository @Inject constructor(
    private val iNetworkData: INetworkData,
    private val proxyDataSource: ProxyDataSource,
    private val defaultDataSourceMode: DataSourceMode
) {

    suspend fun getAllCoins(
        request: GetAllCoinRequest,
        dataSourceMode: DataSourceMode = defaultDataSourceMode
    ): ResultWrapper<Throwable, List<CoinEntity>> {
        return ResultWrapper.build {
            if (dataSourceMode == DataSourceMode.PROXY) {
                return@build proxyDataSource.fetchAllCoins(request)
            }
            iNetworkData.fetchAllCoins(request)
        }
    }

    suspend fun getCoinDetail(
        request: GetCoinDetailRequest,
        dataSourceMode: DataSourceMode = defaultDataSourceMode
    ): ResultWrapper<Throwable, CoinEntityDetail> {
        return ResultWrapper.build {
            if (dataSourceMode == DataSourceMode.PROXY) {
                return@build proxyDataSource.fetchCoinDetail(request)
            }
            iNetworkData.fetchCoinDetail(request)
        }
    }

}