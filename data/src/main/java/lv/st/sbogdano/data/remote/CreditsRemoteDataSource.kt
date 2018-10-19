package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.CreditRemoteModel

class CreditsRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAllById(id: Int): Observable<List<CreditRemoteModel>> = cinemaService.getCreditsById(id)
}