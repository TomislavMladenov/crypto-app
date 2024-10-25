package com.example.cryptoapp.datasource.databse.currencies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.core.model.CryptoCurrency

@Entity(tableName = "crypto_currency")
data class CryptoCurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val symbol: String,
    val priceChange: Float,
    val priceChangePercent: Float,
    val weightedAvgPrice: Float,
    val prevClosePrice: Float,
    val lastPrice: Float,
    val lastQty: Float,
    val bidPrice: Float,
    val bidQty: Float,
    val askPrice: Float,
    val askQty: Float,
    val openPrice: Float,
    val highPrice: Float,
    val lowPrice: Float,
    val volume: Float,
    val quoteVolume: Float,
    val openTime: Long,
    val closeTime: Long,
    val firstId: Long,
    val lastId: Long,
    val count: Int
)