package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Video")
data class VideoLocalModel(
        @PrimaryKey
        val id: Int,
        val key: String,
        val name: String
)
