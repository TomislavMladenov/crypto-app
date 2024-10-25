package com.example.cryptoapp.datasource.databse.currencies

import com.example.cryptoapp.core.currencies.CryptoCurrencyOffline
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.core.util.safeCall
import com.example.cryptoapp.datasource.databse.currencies.dao.CryptoCurrencyDao
import com.example.cryptoapp.datasource.databse.currencies.model.toDomain
import com.example.cryptoapp.datasource.databse.currencies.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CryptoCurrencyRoom(
    private val dao: CryptoCurrencyDao
) : CryptoCurrencyOffline {

    override suspend fun getCryptoCurrencies(): Result<Flow<List<CryptoCurrency>>> {
        return safeCall { dao.getAllCryptoCurrencies().map { list -> list.map { it.toDomain() } } }
    }

    override suspend fun setCryptoCurrencies(currencies: List<CryptoCurrency>) = safeCall {
        dao.insertCurrencies(currencies.map { it.toEntity() })
    }

}