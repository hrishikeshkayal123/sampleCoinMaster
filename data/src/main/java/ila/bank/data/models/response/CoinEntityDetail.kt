package ila.bank.data.models.response



data class CoinEntityDetail(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val description: CoinEntityDetailDescription?
)