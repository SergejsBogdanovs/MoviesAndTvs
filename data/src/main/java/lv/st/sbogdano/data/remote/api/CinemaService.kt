package lv.st.sbogdano.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Observable
import lv.st.sbogdano.data.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaService {

    @GET("movie/{type}")
    @Wrapped(path = ["results"])
    fun getMoviesByType(@Path("type") type: String): Observable<List<MovieRemoteModel>>

    @GET("tv/{type}")
    @Wrapped(path = ["results"])
    fun getTvsByType(@Path("type") type: String): Observable<List<TvRemoteModel>>

    @GET("movie/{movie_id}/credits")
    @Wrapped(path = ["cast"])
    fun getCreditsById(@Path("movie_id") id: Int): Observable<List<CreditRemoteModel>>

    @GET("movie/{movie_id}/videos")
    @Wrapped(path = ["results"])
    fun getVideosById(@Path("movie_id") id: Int): Observable<List<VideoRemoteModel>>

    @GET("movie/{movie_id}/reviews")
    @Wrapped(path = ["results"])
    fun getReviewsById(@Path("movie_id") id: Int): Observable<List<ReviewRemoteModel>>
}