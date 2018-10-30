package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class VideoRemoteModel(
        @Json(name = "id") var id: Int,
        @Json(name = "key") var key: String,
        @Json(name = "name") var name: String
)
