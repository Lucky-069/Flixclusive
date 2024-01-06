package com.flixclusive.core.util.exception

import com.flixclusive.core.util.R
import com.flixclusive.core.util.common.resource.Resource
import com.flixclusive.core.util.log.errorLog
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


/**
 *
 * Catches all internet-related exceptions
 * and returns the message.
 *
 * */
fun Exception.catchInternetRelatedException(): Resource.Failure {
    val defaultMessage = localizedMessage ?: message ?: "Unknown error occurred"
    errorLog(message = defaultMessage)

    val error = when (this) {
        is SocketTimeoutException -> Resource.Failure(R.string.connection_timeout)
        is ConnectException, is UnknownHostException -> Resource.Failure(R.string.connection_failed)
        is HttpException -> {
            val errorMessage = "HTTP error: code ${code()}"
            errorLog(errorMessage)
            Resource.Failure(errorMessage)
        }
        is SSLException -> {
            val errorMessage = "SSL error: $localizedMessage; Check if your system date is correct."
            errorLog(errorMessage)
            Resource.Failure(errorMessage)
        }
        else -> {
            errorLog(defaultMessage)
            Resource.Failure(defaultMessage)
        }
    }

    errorLog(stackTraceToString())
    return error
}