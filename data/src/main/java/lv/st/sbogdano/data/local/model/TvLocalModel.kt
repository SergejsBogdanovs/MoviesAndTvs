package lv.st.sbogdano.data.local.model

import androidx.room.Entity

@Entity(tableName = "Tvs", primaryKeys = ["id", "type"])
data class TvLocalModel(
    var id: Int,
    var posterPath: String?,
    var overview: String,
    var firstAirDate: String,
    var name: String,
    var popularity: Float,
    var voteCount: Int,
    var voteAverage: Float,
    var type: String
)
