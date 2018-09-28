package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.MovieDao
import lv.st.sbogdano.data.local.model.MovieLocalModel

class MovieLocalDataSource(private val movieDao: MovieDao){

    fun getAll(type: String): Observable<List<MovieLocalModel>> = movieDao.getAll(type).toObservable()

    fun insertAll(movies: List<MovieLocalModel>) = movieDao.insertAll(*movies.toTypedArray())

    fun deleteByType(type: String) = movieDao.deleteByType(type)
}