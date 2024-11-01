package com.example.cryptoapp.datasource.databse.currencies

import com.example.cryptoapp.core.currencies.CryptoCurrencyOffline
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.core.util.safeCall
import com.example.cryptoapp.core.util.safeSuspendCall
import com.example.cryptoapp.datasource.databse.currencies.dao.CryptoCurrencyDao
import com.example.cryptoapp.datasource.databse.currencies.model.toDomain
import com.example.cryptoapp.datasource.databse.currencies.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class CryptoCurrencyRoom(
    private val dao: CryptoCurrencyDao
) : CryptoCurrencyOffline {

    override  fun getCryptoCurrencies(): Result<Flow<List<CryptoCurrency>>> {
        return safeCall { dao.getAllCryptoCurrencies().
            distinctUntilChanged().map { list -> list.map { it.toDomain() } } }
    }

    override suspend fun setCryptoCurrencies(currencies: List<CryptoCurrency>) = safeSuspendCall {
        dao.updateCryptoCurrencies(currencies.map { it.toEntity() })
    }

}