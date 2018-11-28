package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.entity.*

interface Gateway {

    fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>>

    fun getTvByType(type: String, refresh: Boolean): Observable<List<Tv>>

    fun getMovieById(id: Int): Observable<Movie>

    fun getTvById(id: Int): Observable<Tv>

    fun getCreditsById(params: Pair<Int, String>): Observable<List<Credit>>

    fun getVideosById(params: Pair<Int, String>): Observable<List<Video>>

    fun getReviewsById(params: Pair<Int, String>): Observable<List<Review>>
}