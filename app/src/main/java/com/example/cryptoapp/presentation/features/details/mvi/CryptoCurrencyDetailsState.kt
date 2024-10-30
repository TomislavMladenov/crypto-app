package com.example.cryptoapp.presentation.features.details.mvi

import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.presentation.base.SimpleViewState

data class CryptoCurrencyDetailsState(
    val currencyState: SimpleViewState<CryptoCurrency> = SimpleViewState.Loading
)
