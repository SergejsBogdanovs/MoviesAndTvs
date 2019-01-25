package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.MovieCreditsDao
import lv.st.sbogdano.data.local.model.MovieCreditLocalModel

class MovieCreditsLocalDataSource(private val movieCreditDao: MovieCreditsDao) {

    fun getById(id: Int): Observable<List<MovieCreditLocalModel>> = movieCreditDao.getById(id).toObservable()

    fun insertAll(movieCredits: List<MovieCreditLocalModel>) = movieCreditDao.insertAll(*movieCredits.toTypedArray())
}