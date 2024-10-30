package com.example.cryptoapp.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.core.repository.CryptoCurrencyRepository
import com.example.cryptoapp.core.util.Constant
import com.example.cryptoapp.presentation.base.BaseViewModel
import com.example.cryptoapp.presentation.base.SimpleViewState
import com.example.cryptoapp.presentation.features.details.mvi.CryptoCurrencyDetailsAction
import com.example.cryptoapp.presentation.features.details.mvi.CryptoCurrencyDetailsState
import com.example.cryptoapp.presentation.navigation.NavigationCommand
import com.example.cryptoapp.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCurrencyDetailsViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager,
    private val repository: CryptoCurrencyRepository
) : BaseViewModel<CryptoCurrencyDetailsState, CryptoCurrencyDetailsAction>(
    CryptoCurrencyDetailsState()
) {

    private val symbol: String =
        checkNotNull(savedStateHandle[Constant.NavArgs.CURRENCY_SYMBOL_ARG])

    init {
        getCurrency(symbol)
    }

    override suspend fun handleActions(action: CryptoCurrencyDetailsAction) {
        when (action) {
            CryptoCurrencyDetailsAction.OnBack -> back()
        }
    }

    private fun getCurrency(symbol: String) {
        viewModelScope.launch {
            val currencyState = repository.getCurrency(symbol)?.let { SimpleViewState.Data(it) }
                ?: SimpleViewState.Error("Unable to find crypto currency")
            updateState { copy(currencyState = currencyState) }
        }
    }

    private suspend fun back() {
        navigationManager.navigate(NavigationCommand.Back)
    }
}