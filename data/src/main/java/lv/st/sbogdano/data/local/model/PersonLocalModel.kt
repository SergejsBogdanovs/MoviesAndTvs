package lv.st.sbogdano.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Persons")
data class PersonLocalModel(
        @PrimaryKey
        val id: Int,
        val name: String,
        val birthDay: String,
        val knownForDepartment: String,
        val gender: Int,
        val biography: String,
        val placeOfBirth: String?,
        val profilePath: String?,
        val homePage: String?
)
