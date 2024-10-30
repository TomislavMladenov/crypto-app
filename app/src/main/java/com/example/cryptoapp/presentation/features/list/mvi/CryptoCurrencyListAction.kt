package com.example.cryptoapp.presentation.features.list.mvi

import com.example.cryptoapp.core.model.CryptoCurrency

sealed interface CryptoCurrencyListAction {
    data class OnSelect(val cryptoCurrency: CryptoCurrency): CryptoCurrencyListAction
    data object OnPullToRefresh : CryptoCurrencyListAction
    data object OnRefresh : CryptoCurrencyListAction
}