package ila.bank.data.source.proxy.coins

import ila.bank.data.models.requests.GetCoinDetailRequest
import ila.bank.data.models.response.CoinEntityDetail
import ila.bank.data.models.response.CoinEntityDetailDescription
import ila.bank.data.source.proxy.ProxyService


class FetchCoinsDetail : ProxyService<GetCoinDetailRequest, CoinEntityDetail>() {


    override suspend fun execute(request: GetCoinDetailRequest): CoinEntityDetail {
        return request.proceed()
    }

    override fun mapProxyResponse(): CoinEntityDetail = CoinEntityDetail(
        "1",
        "BitCoin",
        "Bit Coin",
        CoinEntityDetailDescription("Bitcoin wallet holding Bitcoin cryptocurrency coins")
    )

}