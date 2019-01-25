package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.MovieCreditRemoteModel

class MovieCreditsRemoteDataSource(private val cinemaService: CinemaService) {
    fun getById(id: Int): Observable<List<MovieCreditRemoteModel>> = cinemaService.getMovieCredits(id)
}