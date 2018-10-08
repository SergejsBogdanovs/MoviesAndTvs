package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.TvRemoteModel

class TvRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAll(type: String): Observable<List<TvRemoteModel>> = cinemaService.getTvsByType(type)
}