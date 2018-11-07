package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Credits")
data class CreditLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
)