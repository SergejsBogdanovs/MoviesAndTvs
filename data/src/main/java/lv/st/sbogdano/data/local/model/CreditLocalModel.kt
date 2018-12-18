package lv.st.sbogdano.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Credits")
data class CreditLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
)