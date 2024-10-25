package com.example.cryptoapp.datasource.network.currencies

import com.example.cryptoapp.core.currencies.CryptoCurrencyNetwork
import com.example.cryptoapp.core.util.safeCall
import com.example.cryptoapp.datasource.network.currencies.api.BinanceApi
import com.example.cryptoapp.datasource.network.currencies.model.toDomain

class CryptoCurrencyBinance(
    private val api: BinanceApi
) : CryptoCurrencyNetwork {

    override suspend fun getCryptoCurrencies() = safeCall {
        api.getCryptoCurrenciesForLastDay().map { it.toDomain() }
    }
}