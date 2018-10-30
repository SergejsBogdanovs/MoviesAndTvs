package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.TvLocalDataSource
import lv.st.sbogdano.data.local.model.TvLocalModel
import lv.st.sbogdano.data.remote.TvRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.TvListMapper

class TvRepository(
    private val tvLocalDataSource: TvLocalDataSource,
    private val tvRemoteDataSource: TvRemoteDataSource,
    private val tvListMapper: TvListMapper
) {

    fun getAll(type: String, refresh: Boolean): Observable<List<TvLocalModel>> {

        val local = tvLocalDataSource.getAll(type)
                .filter { !it.isEmpty() }

        val remote = tvRemoteDataSource.getAll(type)
                .map { tvListMapper.toLocal(it, type) }
                .doOnNext { tvLocalDataSource.insertAll(it) }

        return Observable.just(refresh)
                .doOnNext { if (it) tvLocalDataSource.deleteByType(type) }
                .flatMap {
                    Observable.concat(local, remote)
                            .firstElement()
                            .toObservable()
                }
    }
}