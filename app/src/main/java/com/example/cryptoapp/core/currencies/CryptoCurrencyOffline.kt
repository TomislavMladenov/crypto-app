package com.example.cryptoapp.core.currencies

import com.example.cryptoapp.core.model.CryptoCurrency
import kotlinx.coroutines.flow.Flow

interface CryptoCurrencyOffline {

    fun getCryptoCurrencies(): Result<Flow<List<CryptoCurrency>>>

    suspend fun setCryptoCurrencies(currencies: List<CryptoCurrency>) : Result<Unit>

}