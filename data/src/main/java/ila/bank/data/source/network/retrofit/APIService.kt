package ila.bank.data.source.network.retrofit

import ila.bank.data.models.response.CoinEntity
import ila.bank.data.models.response.CoinEntityDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



/**
 * A contract file or an interface of Network call includes in retrofit
 * we mention the type of request/response methods such as GET, POST,etc
 * */
interface APIService {

    @GET("{endPath}")
    suspend fun getAllCoins(
        @Path("endPath", encoded = true) path: String,
        @Query("vs_currency") vs_currency: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean
    ): List<CoinEntity>


    @GET("{endPath}")
    suspend fun getCoinDetail(
        @Path("endPath", encoded = true) path: String
    ): CoinEntityDetail
}