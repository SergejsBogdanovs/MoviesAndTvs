package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.VideoLocalModel
import lv.st.sbogdano.data.remote.model.VideoRemoteModel

class VideosListMapper {

    private fun toLocal(video: VideoRemoteModel) = VideoLocalModel(
            video.id,
            video.key,
            video.name
    )

    fun toLocal(items: List<VideoRemoteModel>) = items.map { toLocal(it) }
}