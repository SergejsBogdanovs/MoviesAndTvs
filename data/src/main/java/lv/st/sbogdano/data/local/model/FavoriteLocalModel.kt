package lv.st.sbogdano.data.local.model

import androidx.room.Entity

@Entity(tableName = "Favorites", primaryKeys = ["id"])
data class FavoriteLocalModel(
    var id: Int,
    var posterPath: String?,
    var type: String
)