package com.example.cryptoapp.presentation.features.list

import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.core.repository.CryptoCurrencyRepository
import com.example.cryptoapp.presentation.base.BaseViewModel
import com.example.cryptoapp.presentation.base.SimpleViewState
import com.example.cryptoapp.presentation.features.list.mvi.CryptoCurrencyListAction
import com.example.cryptoapp.presentation.features.list.mvi.CryptoCurrencyListState
import com.example.cryptoapp.presentation.navigation.CryptoAppDestination
import com.example.cryptoapp.presentation.navigation.NavigationCommand
import com.example.cryptoapp.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyListViewModel
@Inject
constructor(
    private val navigationManager: NavigationManager,
    private val repository: CryptoCurrencyRepository
) : BaseViewModel<CryptoCurrencyListState, CryptoCurrencyListAction>(
    CryptoCurrencyListState()
) {

    init {
        subscribeObservers()
    }

    override suspend fun handleActions(action: CryptoCurrencyListAction) {
        when (action) {
            CryptoCurrencyListAction.OnPullToRefresh -> refresh()
            is CryptoCurrencyListAction.OnSelect -> select(action.cryptoCurrency)
            CryptoCurrencyListAction.OnRefresh -> refresh()
        }
    }

    private fun subscribeObservers() {
        repository.cryptoCurrencies.onEach {
            updateState { copy(cryptoCurrencies = SimpleViewState.Data(it)) }
        }.launchIn(viewModelScope)
    }

    private suspend fun select(currency: CryptoCurrency) {
        navigationManager.navigate(
            NavigationCommand.Navigate(
                destination = CryptoAppDestination.Details(symbol = currency.symbol)
            )
        )
    }

    private suspend fun refresh() {
        updateState { copy(isDataRefreshing = true) }
        //Just for decoration request goes too fast, show loading as per requirement
        delay(1000)
        repository.refresh()
        updateState { copy(isDataRefreshing = false) }
    }
}