package lv.st.sbogdano.data.remote.api

import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Observable
import lv.st.sbogdano.data.remote.model.GenreTypeRemoteModel
import retrofit2.http.GET

interface CinemaService {

    @GET("genre/movie/list")
    @Wrapped(path = ["genres"])
    fun getGenreTypes(): Observable<List<GenreTypeRemoteModel>>

}