package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.VideosLocalDataSource
import lv.st.sbogdano.data.local.model.VideoLocalModel
import lv.st.sbogdano.data.remote.VideosRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.VideosListMapper

class VideosRepository(
    private val videosLocalDataSource: VideosLocalDataSource,
    private val videosRemoteDataSource: VideosRemoteDataSource,
    private val videosListMapper: VideosListMapper
) {

    fun getAllById(params: Pair<Int, String>): Observable<List<VideoLocalModel>> {

        val (id, path) = params

        val local = videosLocalDataSource.getAllById(id)
                .filter { !it.isEmpty() }

        val remote = videosRemoteDataSource.getAllById(id, path)
                .map { it.take(1) }
                .map { videosListMapper.toLocal(it) }
                .doOnNext { videosLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}