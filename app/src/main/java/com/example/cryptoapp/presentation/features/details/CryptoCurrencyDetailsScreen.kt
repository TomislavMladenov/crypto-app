package com.example.cryptoapp.presentation.features.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CryptoCurrencyDetailsScreen() {

    val viewModel = hiltViewModel<CryptoCurrencyDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    CryptoCurrencyDetailsContent(state) { viewModel.submitAction(it) }
}