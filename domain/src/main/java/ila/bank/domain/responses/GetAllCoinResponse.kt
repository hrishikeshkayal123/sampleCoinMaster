package ila.bank.domain.responses


data class GetAllCoinResponse(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    val price: Double
)
