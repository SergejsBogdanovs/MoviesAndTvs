package lv.st.sbogdano.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Observable
import lv.st.sbogdano.data.remote.model.MovieRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaService {

    @GET("movie/{type}")
    @Wrapped(path = ["results"])
    fun getMoviesByType(@Path("type") type: String): Observable<List<MovieRemoteModel>>

}