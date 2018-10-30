package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.VideosDao
import lv.st.sbogdano.data.local.model.VideoLocalModel

class VideosLocalDataSource(private val videosDao: VideosDao) {

    fun getAllById(id: Int): Observable<List<VideoLocalModel>> = videosDao.getAllById(id).toObservable()

    fun insertAll(videos: List<VideoLocalModel>) = videosDao.insertAll(*videos.toTypedArray())
}