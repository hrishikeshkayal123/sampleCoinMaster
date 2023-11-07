package ila.bank.data.models.requests



data class GetCoinDetailRequest(
    val id: String
) : ApisRequest("api/v3/coins/$id")

