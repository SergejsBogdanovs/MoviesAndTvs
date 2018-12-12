package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.MoviesDao
import lv.st.sbogdano.data.local.model.MovieLocalModel

class MovieLocalDataSource(private val moviesDao: MoviesDao) {

    fun getAll(type: String): Observable<List<MovieLocalModel>> = moviesDao.getAll(type).toObservable()

    fun insertAll(movies: List<MovieLocalModel>) = moviesDao.insertAll(*movies.toTypedArray())

    fun deleteByType(type: String) = moviesDao.deleteByType(type)

    fun getById(id: Int) = moviesDao.getById(id).toObservable()!!
}