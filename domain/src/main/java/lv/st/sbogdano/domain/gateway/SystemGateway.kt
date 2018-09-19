package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.entity.GenreType

interface SystemGateway {
    fun getGenreTypes(): Observable<List<GenreType>>
}