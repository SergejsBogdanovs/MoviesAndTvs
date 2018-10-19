package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class CreditRemoteModel(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "character") val character: String,
        @Json(name = "profile_path") val profilePath: String?
)