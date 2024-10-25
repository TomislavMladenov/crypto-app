package com.example.cryptoapp.datasource.network.currencies.api

import com.example.cryptoapp.datasource.network.currencies.model.CryptoCurrencyDto
import retrofit2.http.GET

interface BinanceApi {

    companion object {
        private const val TICKER_24H = "api/v3/ticker/24hr"
    }

    @GET(TICKER_24H)
    suspend fun getCryptoCurrenciesForLastDay() : List<CryptoCurrencyDto>
}