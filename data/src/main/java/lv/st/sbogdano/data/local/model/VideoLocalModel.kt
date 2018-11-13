package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Videos")
data class VideoLocalModel(
    @PrimaryKey
    val id: String,
    val key: String,
    val name: String
)
