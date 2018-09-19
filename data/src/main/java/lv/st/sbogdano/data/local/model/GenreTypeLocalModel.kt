package lv.st.sbogdano.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "GenreType")
data class GenreTypeLocalModel(
        @PrimaryKey var id: Int,
        var name: String
)