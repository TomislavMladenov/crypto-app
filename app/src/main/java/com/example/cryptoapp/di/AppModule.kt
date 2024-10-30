package com.example.cryptoapp.di

import com.example.cryptoapp.core.currencies.CryptoCurrencyNetwork
import com.example.cryptoapp.core.currencies.CryptoCurrencyOffline
import com.example.cryptoapp.core.repository.CryptoCurrencyRepository
import com.example.cryptoapp.core.repository.CryptoCurrencyRepositoryImpl
import com.example.cryptoapp.presentation.navigation.NavigationManager
import com.example.cryptoapp.presentation.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    fun providesCoroutineScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun provideCryptoCurrencyRepository(
        cryptoCurrencyNetwork: CryptoCurrencyNetwork,
        cryptoCurrencyOffline: CryptoCurrencyOffline,
        @ApplicationScope applicationScope: CoroutineScope
    ): CryptoCurrencyRepository = CryptoCurrencyRepositoryImpl(
        cryptoCurrencyNetwork, cryptoCurrencyOffline, applicationScope
    )

    @Singleton
    @Provides
    fun provideNavigationManager(): NavigationManager = NavigationManagerImpl()
}