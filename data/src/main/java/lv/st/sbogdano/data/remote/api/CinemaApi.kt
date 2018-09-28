package lv.st.sbogdano.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import lv.st.sbogdano.data.BuildConfig
import lv.st.sbogdano.data.remote.api.util.AuthenticatorInterceptor
import lv.st.sbogdano.data.remote.api.util.MoshiConverters
import lv.st.sbogdano.data.remote.api.util.RetryAfterInterceptor
import lv.st.sbogdano.data.remote.model.MovieRemoteModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class CinemaApi(baseUrl: String) : CinemaService {

    companion object {
        private const val TIMEOUT = 10L
    }

    private val service: CinemaService

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) BODY else BASIC

        val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(AuthenticatorInterceptor())
                .addInterceptor(RetryAfterInterceptor())

        val client = httpClient.build()

        val moshi = Moshi.Builder()
                .add(Wrapped.ADAPTER_FACTORY)
                .add(MoshiConverters())
                .add(KotlinJsonAdapterFactory())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        service = retrofit.create(CinemaService::class.java)
    }

    override fun getMoviesByType(type: String): Observable<List<MovieRemoteModel>> = service.getMoviesByType(type)

}