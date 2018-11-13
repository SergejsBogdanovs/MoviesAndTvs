package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Reviews")
data class ReviewLocalModel(
    @PrimaryKey
    val id: String,
    val author: String,
    val content: String
)