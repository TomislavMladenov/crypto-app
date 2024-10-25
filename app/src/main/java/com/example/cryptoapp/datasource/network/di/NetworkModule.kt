@file:OptIn(ExperimentalSerializationApi::class)

package com.example.cryptoapp.datasource.network.di

import com.example.cryptoapp.BuildConfig
import com.example.cryptoapp.datasource.network.currencies.CryptoCurrencyBinance
import com.example.cryptoapp.datasource.network.currencies.api.BinanceApi
import com.example.cryptoapp.datasource.network.util.NetworkConstants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        val contentType = "application/json".toMediaType()
//        val json = Json {
//            // If the server send extra fields than the data class -> ignore them
//            ignoreUnknownKeys = true
//            isLenient = true
//            encodeDefaults = true
//            explicitNulls = false
//        }
//        return Retrofit.Builder()
//            .baseUrl(NetworkConstants.BASE_URL)
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .client(
//                OkHttpClient.Builder()
//                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .readTimeout(15, TimeUnit.SECONDS)
//                    .writeTimeout(15, TimeUnit.SECONDS)
//                    .addInterceptor(
//                        HttpLoggingInterceptor().apply {
//                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//                            else HttpLoggingInterceptor.Level.NONE
//                        }).build()
//            ).build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideBinanceApi(retrofit: Retrofit): BinanceApi = retrofit.create(BinanceApi::class.java)
//
//    @Singleton
//    @Provides
//    fun provideCryptoCurrencyBinance(
//        binanceApi: BinanceApi
//    ) = CryptoCurrencyBinance(binanceApi)
//
//}