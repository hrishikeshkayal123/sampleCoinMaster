package ila.bank.data.source.proxy.coins

import ila.bank.data.models.requests.GetAllCoinRequest
import ila.bank.data.models.response.CoinEntity
import ila.bank.data.source.proxy.ProxyService
import kotlinx.coroutines.delay


class FetchAllCoins : ProxyService<GetAllCoinRequest, List<CoinEntity>>() {

    override suspend fun execute(request: GetAllCoinRequest): List<CoinEntity> {
        return request.proceed()
    }

    override fun mapProxyResponse(): List<CoinEntity> = arrayListOf<CoinEntity>().apply {
        add(
            CoinEntity(
                "1",
                50.89,
                "https://d32ijn7u0aqfv4.cloudfront.net/wp/wp-content/uploads/raw" +
                        "/SOIN0521188_1560x880_desktop.jpg",
                "Bit Coin",
                60.12,
                "Bitcoin"
            )
        )
        add(
            CoinEntity(
                "2",
                75.33,
                "https://c8.alamy.com/" +
                        "comp/M5A7FW/" +
                        "bitcoin-wallet-holding-bitcoin-cryptocurrency-coins" +
                        "-lying-on-a-pile-M5A7FW.jpg",
                "Ethereum",
                90.75,
                "Ethereum"
            )
        )
        add(
            CoinEntity(
                "3",
                75.33,
                "https://c8.alamy.com/" +
                        "comp/M5A7FW/" +
                        "bitcoin-wallet-holding-bitcoin-cryptocurrency-coins" +
                        "-lying-on-a-pile-M5A7FW.jpg",
                "XRP",
                90.75,
                "XRP"
            )
        )
        add(
            CoinEntity(
                "4",
                75.33,
                "https://c8.alamy.com/" +
                        "comp/M5A7FW/" +
                        "bitcoin-wallet-holding-bitcoin-cryptocurrency-coins" +
                        "-lying-on-a-pile-M5A7FW.jpg",
                "Cardano",
                90.75,
                "Cardano"
            )
        )
        add(
            CoinEntity(
                "5",
                75.33,
                "https://c8.alamy.com/" +
                        "comp/M5A7FW/" +
                        "bitcoin-wallet-holding-bitcoin-cryptocurrency-coins" +
                        "-lying-on-a-pile-M5A7FW.jpg",
                "Premium Bit Coin",
                90.75,
                "USD Coin"
            )
        )
    }

}