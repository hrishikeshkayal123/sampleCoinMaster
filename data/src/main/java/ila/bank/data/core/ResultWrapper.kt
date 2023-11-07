package ila.bank.data.core

import ila.bank.data.core.ApiException.Companion.mapToApiException
import java.io.IOException



sealed class ResultWrapper<out E, out V> {

    data class Success<out V>(val value: V) : ResultWrapper<Nothing, V>()
    data class Error<out E>(val error: E) : ResultWrapper<E, Nothing>()

    companion object Factory {

        inline fun <V> build(function: () -> V): ResultWrapper<Throwable, V> =
            try {
                Success(function.invoke())
            } catch (th: Throwable) {
                Error(th)
            }
    }
}