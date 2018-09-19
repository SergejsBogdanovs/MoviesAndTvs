package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.GenreTypeRemoteModel

class GenreTypeRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAll(): Observable<List<GenreTypeRemoteModel>> = cinemaService.getGenreTypes()
}