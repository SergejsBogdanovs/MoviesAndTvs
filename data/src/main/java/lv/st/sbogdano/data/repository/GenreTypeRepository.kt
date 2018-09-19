package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.GenreTypeLocalDataSource
import lv.st.sbogdano.data.local.model.GenreTypeLocalModel
import lv.st.sbogdano.data.remote.GenreTypeRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.GenreTypeMapper

class GenreTypeRepository(
        private val genreTypeLocalDataSource: GenreTypeLocalDataSource,
        private val genreTypeRemoteDataSource: GenreTypeRemoteDataSource,
        private val genreTypeMapper: GenreTypeMapper) {

    fun getAll(): Observable<List<GenreTypeLocalModel>> {

        val local = genreTypeLocalDataSource.getAll()
                .filter { !it.isEmpty() }

        val remote = genreTypeRemoteDataSource.getAll()
                .map { genreTypeMapper.toLocal(it) }
                .doOnNext { genreTypeLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}