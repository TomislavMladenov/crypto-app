package com.example.cryptoapp.datasource.databse.currencies.model

import com.example.cryptoapp.core.model.CryptoCurrency

fun CryptoCurrency.toEntity() = CryptoCurrencyEntity(
    symbol = symbol,
    priceChange = priceChange,
    priceChangePercent = priceChangePercent,
    weightedAvgPrice = weightedAvgPrice,
    prevClosePrice = prevClosePrice,
    lastPrice = lastPrice,
    lastQty = lastQty,
    bidPrice = bidPrice,
    bidQty = bidQty,
    askPrice = askPrice,
    askQty = askQty,
    openPrice = openPrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    volume = volume,
    quoteVolume = quoteVolume,
    openTime = openTime,
    closeTime = closeTime,
    firstId = firstId,
    lastId = lastId,
    count = count
)

fun CryptoCurrencyEntity.toDomain() = CryptoCurrency(
    symbol = symbol,
    priceChange = priceChange,
    priceChangePercent = priceChangePercent,
    weightedAvgPrice = weightedAvgPrice,
    prevClosePrice = prevClosePrice,
    lastPrice = lastPrice,
    lastQty = lastQty,
    bidPrice = bidPrice,
    bidQty = bidQty,
    askPrice = askPrice,
    askQty = askQty,
    openPrice = openPrice,
    highPrice = highPrice,
    lowPrice = lowPrice,
    volume = volume,
    quoteVolume = quoteVolume,
    openTime = openTime,
    closeTime = closeTime,
    firstId = firstId,
    lastId = lastId,
    count = count
)