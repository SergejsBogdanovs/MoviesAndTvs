package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.VideoRemoteModel

class VideosRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAllById(id: Int, path: String): Observable<List<VideoRemoteModel>> = cinemaService.getVideosById(id, path)
}