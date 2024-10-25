package com.example.cryptoapp.core.currencies

import com.example.cryptoapp.core.model.CryptoCurrency

interface CryptoCurrencyNetwork {

    suspend fun getCryptoCurrencies(): Result<List<CryptoCurrency>>

}