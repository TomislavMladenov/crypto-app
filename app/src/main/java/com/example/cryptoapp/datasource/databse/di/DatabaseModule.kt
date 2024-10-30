package com.example.cryptoapp.datasource.databse.di

import android.content.Context
import androidx.room.Room
import com.example.cryptoapp.core.currencies.CryptoCurrencyOffline
import com.example.cryptoapp.datasource.databse.currencies.CryptoCurrencyRoom
import com.example.cryptoapp.datasource.databse.currencies.dao.CryptoCurrencyDao
import com.example.cryptoapp.datasource.databse.currencies.dao.CryptoDatabase
import com.example.cryptoapp.datasource.databse.util.DatabaseConst.CRYPTO_DATABASE
import com.example.cryptoapp.datasource.network.currencies.CryptoCurrencyBinance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CryptoDatabase::class.java, CRYPTO_DATABASE
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCurrencyDao(db: CryptoDatabase) = db.cryptoCurrencyDao()

    @Singleton
    @Provides
    fun provideCryptoCurrencyDatabase(db: CryptoDatabase): CryptoCurrencyOffline =
        CryptoCurrencyRoom(db.cryptoCurrencyDao())

}