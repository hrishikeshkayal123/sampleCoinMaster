package ila.bank.carousel_demo.features.dashboard.models



/**
 * CoinItemData is model class for listing in @CoinsAdapter
 * */
data class CoinItemData(
    val coinId: String,
    val image: String,
    val coinName: String,
    val overview: String
)