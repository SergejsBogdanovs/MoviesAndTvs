package lv.st.sbogdano.data.remote.api.util

import io.reactivex.exceptions.Exceptions
import lv.st.sbogdano.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticatorInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()

        val url = currentRequest.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

        // Error handling in RxJava
        // http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/
        try {
            return chain.proceed(currentRequest.newBuilder().url(url).build())
        } catch (e: IOException) {
            throw Exceptions.propagate(e)
        }
    }
}