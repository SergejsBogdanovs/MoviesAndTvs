package lv.st.sbogdano.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Videos")
data class VideoLocalModel(
    @PrimaryKey
    val id: String,
    val key: String,
    val name: String
)
