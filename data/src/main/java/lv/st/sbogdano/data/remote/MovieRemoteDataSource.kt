package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.MovieRemoteModel

class MovieRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAll(type: String): Observable<List<MovieRemoteModel>> = cinemaService.getMoviesByType(type)
}