package lv.st.sbogdano.data.remote.api.util

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.net.HttpURLConnection

/**
 * Retry the request in case of "Account Over Queries Per Second Limit" error
 */
class RetryAfterInterceptor : Interceptor{

    private val logger = HttpLoggingInterceptor.Logger.DEFAULT

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)

        if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
            val retryAfter = parseRetryAfter(response)
            if (retryAfter != null) {
                logger.log("Retrying after $retryAfter seconds...")
                Thread.sleep((retryAfter * 1000).toLong())
                response = chain.proceed(request)
            }
        }

        return response
    }

    private fun parseRetryAfter(response: Response): Int? {
        val retryAfter = response.header("Retry-After")
        return retryAfter?.toInt()
    }
}