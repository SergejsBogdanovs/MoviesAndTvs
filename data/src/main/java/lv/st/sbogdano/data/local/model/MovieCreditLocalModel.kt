package lv.st.sbogdano.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieCredits")
data class MovieCreditLocalModel(
    @PrimaryKey
    val id: Int,
    val posterPath: String?,
    val voteAverage: Float
)