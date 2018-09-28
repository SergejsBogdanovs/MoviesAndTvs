package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieLocalModel(
        @PrimaryKey var id: Int,
        var posterPath: String?,
        var overview: String,
        var releaseDate: String,
        var title: String,
        var popularity: Int,
        var voteCount: Int,
        var voteAverage: Int,
        var type: String
)