package com.example.cryptoapp.core.repository

import com.example.cryptoapp.core.model.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface CryptoCurrencyRepository {

    val cryptoCurrencies: Flow<List<CryptoCurrency>>

    suspend fun refresh()

    suspend fun getCurrency(symbol: String): CryptoCurrency?
}