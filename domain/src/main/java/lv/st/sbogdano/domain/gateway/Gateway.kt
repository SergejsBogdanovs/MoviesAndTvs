package lv.st.sbogdano.domain.gateway

import io.reactivex.Observable
import lv.st.sbogdano.domain.model.*

interface Gateway {

    fun getMoviesByType(type: String, refresh: Boolean): Observable<List<MovieDomainModel>>

    fun getTvByType(type: String, refresh: Boolean): Observable<List<TvDomainModel>>

    fun getMovieById(id: Int): Observable<MovieDomainModel>

    fun getTvById(id: Int): Observable<TvDomainModel>

    fun getCreditsById(params: Pair<Int, String>): Observable<List<CreditDomainModel>>

    fun getVideosById(params: Pair<Int, String>): Observable<List<VideoDomainModel>>

    fun getReviewsById(params: Pair<Int, String>): Observable<List<ReviewDomainModel>>

    fun addToFavorites(params: Pair<MovieDomainModel, String>): Observable<Long>
}