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

    @GET("{path}/{id}/credits")
    @Wrapped(path = ["cast"])
    fun getCreditsById(@Path("id") id: Int, @Path("path") path: String): Observable<List<CreditRemoteModel>>

    @GET("{path}/{id}/videos")
    @Wrapped(path = ["results"])
    fun getVideosById(@Path("id") id: Int, @Path("path") path: String): Observable<List<VideoRemoteModel>>

    @GET("{path}/{id}/reviews")
    @Wrapped(path = ["results"])
    fun getReviewsById(@Path("id") id: Int, @Path("path") path: String): Observable<List<ReviewRemoteModel>>
}