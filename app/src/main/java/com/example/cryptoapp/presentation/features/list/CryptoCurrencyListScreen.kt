package com.example.cryptoapp.presentation.features.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CryptoCurrencyListScreen() {

    val viewModel: CryptoCurrencyListViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    CryptoCurrencyContent(state) { viewModel.submitAction(it) }
}