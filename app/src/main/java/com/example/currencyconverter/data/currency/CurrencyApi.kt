package com.example.currencyconverter.data.currency

import com.example.currencyconverter.data.currency.model.LatestCurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest.json")
    suspend fun getLatestCurrencyRates(
        @Query("app_id") appId: String,
        @Query("base") base: String
    ): LatestCurrencyResponse

}
