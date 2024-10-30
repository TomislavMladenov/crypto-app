package com.example.cryptoapp.presentation.navigation

sealed class NavigationCommand {
    data class Navigate(val destination: CryptoAppDestination): NavigationCommand()
    data object Back : NavigationCommand()
}