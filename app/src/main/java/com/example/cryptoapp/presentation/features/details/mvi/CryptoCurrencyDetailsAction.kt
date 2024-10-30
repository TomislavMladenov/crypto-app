package com.example.cryptoapp.presentation.features.details.mvi

sealed interface CryptoCurrencyDetailsAction {

    data object OnBack : CryptoCurrencyDetailsAction
}