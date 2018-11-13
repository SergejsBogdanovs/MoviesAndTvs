package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class ReviewRemoteModel(
    @Json(name = "id") val id: String,
    @Json(name = "author") val author: String,
    @Json(name = "content") val content: String
)