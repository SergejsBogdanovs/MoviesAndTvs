package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Tv

interface Gateway {

    fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>>

    fun getTvByType(type: String, refresh: Boolean): Observable<List<Tv>>

    fun getMovieById(id: Int): Observable<Movie>
}