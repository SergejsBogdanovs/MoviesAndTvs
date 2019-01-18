package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.PersonRemoteModel

class PersonRemoteDataSource(private val cinemaService: CinemaService) {

    fun getById(id: Int): Observable<PersonRemoteModel> = cinemaService.getPersonById(id)
}