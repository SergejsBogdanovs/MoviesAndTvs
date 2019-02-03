package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.MovieLocalDataSource
import lv.st.sbogdano.data.local.model.MovieLocalModel
import lv.st.sbogdano.data.remote.MovieRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.MovieListMapper

class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val moviesListMapper: MovieListMapper
) {

    fun getAll(type: String, refresh: Boolean): Observable<List<MovieLocalModel>> {

        val local = movieLocalDataSource.getAll(type)
                .filter { !it.isEmpty() }

        val remote = movieRemoteDataSource.getAll(type)
                .map { moviesListMapper.toLocal(it, type) }
                .doOnNext { movieLocalDataSource.insertAll(it) }

        return Observable.just(refresh)
                .doOnNext { if (refresh) movieLocalDataSource.deleteByType(type) }
                .flatMap {
                    Observable.concat(local, remote)
                            .firstElement()
                            .toObservable()
                }
    }

    fun getById(id: Int): Observable<MovieLocalModel> = movieLocalDataSource.getById(id)
}