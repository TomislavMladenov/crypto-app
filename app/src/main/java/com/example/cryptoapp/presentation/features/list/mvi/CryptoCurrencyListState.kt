package com.example.cryptoapp.presentation.features.list.mvi

import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.presentation.base.SimpleViewState

data class CryptoCurrencyListState(
    val cryptoCurrencies: SimpleViewState<List<CryptoCurrency>> = SimpleViewState.Loading,
    val isDataRefreshing: Boolean = false
)
