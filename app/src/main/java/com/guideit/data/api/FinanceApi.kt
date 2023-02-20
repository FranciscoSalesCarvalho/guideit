package com.guideit.data.api

import com.guideit.data.model.Stock
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface FinanceApi {

    @GET("chart/{stock}")
    suspend fun getStock(
        @Path("stock") stock: String = "PETR4.SA",
        @QueryMap options: Map<String, String> = mapOf("interval" to "1d", "range" to "1mo")
    ): Stock
}