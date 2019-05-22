package lv.st.sbogdano.domain.model

data class PersonDomainModel(
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
