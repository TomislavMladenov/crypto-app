@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cryptoapp.presentation.features.list

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.cryptoapp.R
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.presentation.base.SimpleViewState
import com.example.cryptoapp.presentation.features.list.mvi.CryptoCurrencyListAction
import com.example.cryptoapp.presentation.features.list.mvi.CryptoCurrencyListState
import com.example.cryptoapp.presentation.ui.components.CircularIndeterminateProgressBar
import com.example.cryptoapp.presentation.ui.components.ContentUnavailable
import com.example.cryptoapp.presentation.ui.theme.Dimens

@Composable
fun CryptoCurrencyContent(
    state: CryptoCurrencyListState,
    action: (CryptoCurrencyListAction) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = state.isDataRefreshing,
        onRefresh = { action(CryptoCurrencyListAction.OnPullToRefresh) },
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (state.cryptoCurrencies) {
            is SimpleViewState.Data -> CryptoCurrencyList(state.cryptoCurrencies.value, action)
            is SimpleViewState.Error -> NoResultsFoundView(action)
            SimpleViewState.Loading -> CircularIndeterminateProgressBar()
        }
    }
}

@Composable
private fun CryptoCurrencyList(
    currencies: List<CryptoCurrency>,
    action: (CryptoCurrencyListAction) -> Unit
) {
    if (currencies.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(currencies) { currency ->
                CryptoCurrencyInfoItem(currency) { action(CryptoCurrencyListAction.OnSelect(currency)) }
            }
        }
    } else {
        NoResultsFoundView(action)
    }
}

@Composable
private fun NoResultsFoundView(action: (CryptoCurrencyListAction) -> Unit) {
    ContentUnavailable(
        stringResource(R.string.text_unable_to_fetch_results),
        stringResource(R.string.text_try_again)
    ) {
        action(CryptoCurrencyListAction.OnRefresh)
    }
}


@Composable
private fun CryptoCurrencyInfoItem(
    currency: CryptoCurrency,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(Dimens.medium),
        onClick = onSelect
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.medium, horizontal = Dimens.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CurrencyPrimaryInfo(currency)
            CurrencySecondaryInfo(currency)
        }
    }
}

@Composable
private fun CurrencyPrimaryInfo(
    currency: CryptoCurrency
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currency.symbol,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "${currency.priceChangePercent}%",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun CurrencySecondaryInfo(
    currency: CryptoCurrency
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "bid/ask:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = "${currency.bidPrice}/${currency.askPrice}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}