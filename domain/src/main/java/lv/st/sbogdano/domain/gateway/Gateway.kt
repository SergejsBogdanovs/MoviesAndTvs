package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.entity.Movie

interface Gateway {

    fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>>
}