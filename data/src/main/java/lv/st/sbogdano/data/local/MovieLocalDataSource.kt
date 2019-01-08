package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.MoviesDao
import lv.st.sbogdano.data.local.model.MovieLocalModel

class MovieLocalDataSource(private val moviesDao: MoviesDao) {

    fun getAll(type: String): Observable<List<MovieLocalModel>> = moviesDao.getAll(type).toObservable()

    fun getById(id: Int): Observable<MovieLocalModel> = moviesDao.getById(id).toObservable()!!

    fun insertAll(movieLocalModels: List<MovieLocalModel>) = moviesDao.insertAll(*movieLocalModels.toTypedArray())

    fun deleteByType(type: String) = moviesDao.deleteByType(type)
}