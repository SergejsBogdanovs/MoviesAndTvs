package lv.st.sbogdano.data.local.model

import androidx.room.Entity

@Entity(tableName = "Movies", primaryKeys = ["id", "type", "favorite"])
data class MovieLocalModel(
    var id: Int,
    var posterPath: String?,
    var overview: String,
    var releaseDate: String,
    var title: String,
    var popularity: Float,
    var voteCount: Int,
    var voteAverage: Float,
    var type: String,
    var favorite: Boolean = false
)