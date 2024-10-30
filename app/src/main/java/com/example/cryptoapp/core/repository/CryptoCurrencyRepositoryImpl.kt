package com.example.cryptoapp.core.repository

import com.example.cryptoapp.core.currencies.CryptoCurrencyNetwork
import com.example.cryptoapp.core.currencies.CryptoCurrencyOffline
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoCurrencyRepositoryImpl
@Inject
constructor(
    private val cryptoCurrencyNetwork: CryptoCurrencyNetwork,
    private val cryptoCurrencyOffline: CryptoCurrencyOffline,
    @ApplicationScope
    private val applicationScope: CoroutineScope
) : CryptoCurrencyRepository {

    override val cryptoCurrencies = MutableStateFlow<List<CryptoCurrency>>(emptyList())

    init {
        applicationScope.launch { subscribeObservers() }
        applicationScope.launch { loadNetworkData() }
    }

    private suspend fun loadNetworkData() {
        cryptoCurrencyNetwork.getCryptoCurrencies()
            .onSuccess { apiResult ->
                cryptoCurrencyOffline.setCryptoCurrencies(apiResult)
            }.onFailure { Timber.e(it.message) }

    }

    private fun subscribeObservers() {
            cryptoCurrencyOffline.getCryptoCurrencies()
                .onSuccess {
                    it.onEach { list ->
                        cryptoCurrencies.update { list }
                    }.launchIn(applicationScope)
                }
                .onFailure { Timber.e(it.message) }
    }

    override suspend fun refresh() = loadNetworkData()

    override suspend fun getCurrency(symbol: String) =
        cryptoCurrencies.value.firstOrNull { symbol == it.symbol }

}