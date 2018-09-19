package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.GenreTypeDao
import lv.st.sbogdano.data.local.model.GenreTypeLocalModel

class GenreTypeLocalDataSource(private val genreTypeDao: GenreTypeDao){

    fun getAll(): Observable<List<GenreTypeLocalModel>> = genreTypeDao.getAll().toObservable()

    fun insertAll(genres: List<GenreTypeLocalModel>) = genreTypeDao.insertAll(*genres.toTypedArray())
}