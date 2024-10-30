package com.example.cryptoapp.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.cryptoapp.core.util.Constant

sealed class CryptoAppDestination(
    val destination: String,
    open val args: Map<String, String> = emptyMap()
) {
    data object ListView : CryptoAppDestination("list")
    data class Details(
        val symbol: String = ""
    ) : CryptoAppDestination(
        destination = "details",
        args = mapOf(Pair(Constant.NavArgs.CURRENCY_SYMBOL_ARG, symbol))
    )

    val arguments: List<NamedNavArgument> = buildList {
        args.forEach { element ->
            add(navArgument(element.key) {
                type = NavType.StringType
            })
        }
    }

    val route: String
        get() = buildString {
            append(destination)
            args.forEach { argument ->
                append("/${argument.key}/{${argument.key}}")
            }
        }

    val routePath: String
        get() = buildString {
            append(destination)
            args.forEach { argument ->
                append("/${argument.key}/${argument.value}")
            }
        }
}


