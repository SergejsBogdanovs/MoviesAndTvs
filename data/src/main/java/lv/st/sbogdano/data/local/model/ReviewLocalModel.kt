package lv.st.sbogdano.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reviews")
data class ReviewLocalModel(
    @PrimaryKey
    val id: String,
    val author: String,
    val content: String
)