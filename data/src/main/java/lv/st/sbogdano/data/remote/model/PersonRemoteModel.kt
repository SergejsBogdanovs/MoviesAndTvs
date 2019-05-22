package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class PersonRemoteModel(
    @Json(name = "id") var id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "birthday") val birthDay: String,
    @Json(name = "known_for_department") val knownForDepartment: String,
    @Json(name = "gender") val gender: Int,
    @Json(name = "biography") val biography: String,
    @Json(name = "place_of_birth") val placeOfBirth: String?,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "homepage") val homePage: String?
)
