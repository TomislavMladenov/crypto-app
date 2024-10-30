package com.example.cryptoapp.presentation.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.cryptoapp.R
import com.example.cryptoapp.core.model.CryptoCurrency
import com.example.cryptoapp.presentation.base.SimpleViewState
import com.example.cryptoapp.presentation.features.details.mvi.CryptoCurrencyDetailsAction
import com.example.cryptoapp.presentation.features.details.mvi.CryptoCurrencyDetailsState
import com.example.cryptoapp.presentation.ui.components.CircularIndeterminateProgressBar
import com.example.cryptoapp.presentation.ui.components.ContentUnavailable
import com.example.cryptoapp.presentation.ui.theme.Dimens

@Composable
internal fun CryptoCurrencyDetailsContent(
    state: CryptoCurrencyDetailsState,
    action: (CryptoCurrencyDetailsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = .9f))
            .padding(Dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state.currencyState) {
            is SimpleViewState.Data -> CryptoCurrencyInfo(state.currencyState.value)
            is SimpleViewState.Error -> ContentUnavailable(
                stringResource(R.string.text_content_unavailable),
                stringResource(R.string.text_back)
            ) {
                action(CryptoCurrencyDetailsAction.OnBack)
            }

            SimpleViewState.Loading -> CircularIndeterminateProgressBar()
        }
    }
}

@Composable
private fun CryptoCurrencyInfo(
    currency: CryptoCurrency
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        InfoRow(
            label = stringResource(R.string.text_symbol),
            value = currency.symbol
        )
        InfoRow(
            label = stringResource(R.string.text_price_chance),
            value = currency.priceChange.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_price_chance_per),
            value = currency.priceChangePercent.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_weight_avg_price),
            value = currency.weightedAvgPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_prev_close_price),
            value = currency.prevClosePrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_last_price),
            value = currency.lastPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_last_qty),
            value = currency.lastQty.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_bid_price),
            value = currency.bidPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_bid_qty),
            value = currency.bidQty.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_last_qty),
            value = currency.lastQty.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_bid_price),
            value = currency.bidPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_bid_qty),
            value = currency.bidQty.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_ask_price),
            value = currency.askPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_ask_qty),
            value = currency.askQty.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_open_price),
            value = currency.openPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_high_price),
            value = currency.highPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_low_price),
            value = currency.lowPrice.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_volume),
            value = currency.volume.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_quote_volume),
            value = currency.quoteVolume.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_open_time),
            value = currency.openTime.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_close_time),
            value = currency.closeTime.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_first_id),
            value = currency.firstId.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_last_id),
            value = currency.lastId.toString()
        )
        InfoRow(
            label = stringResource(R.string.text_count),
            value = currency.count.toString()
        )
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.small),
        verticalArrangement = Arrangement.spacedBy(Dimens.small)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}