package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.entity.Video

interface Gateway {

    fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>>

    fun getTvByType(type: String, refresh: Boolean): Observable<List<Tv>>

    fun getMovieById(id: Int): Observable<Movie>

    fun getCreditsById(id: Int): Observable<List<Credit>>

    fun getVideosById(id: Int): Observable<List<Video>>
}