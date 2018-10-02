package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Movie", primaryKeys = ["id", "type"])
data class MovieLocalModel(
        var id: Int,
        var posterPath: String?,
        var overview: String,
        var releaseDate: String,
        var title: String,
        var popularity: Float,
        var voteCount: Int,
        var voteAverage: Float,
        var type: String
)