package com.example.cryptoapp.core.util

import timber.log.Timber

suspend fun <T> safeCall(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (ex: Exception) {
        Timber.e(ex)
        Result.failure(ex)
    }
}