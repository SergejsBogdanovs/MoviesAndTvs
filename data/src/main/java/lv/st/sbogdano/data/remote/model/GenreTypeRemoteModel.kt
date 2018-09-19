package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class GenreTypeRemoteModel(
        @Json(name = "id") var id: Int,
        @Json(name = "name") var name: String)