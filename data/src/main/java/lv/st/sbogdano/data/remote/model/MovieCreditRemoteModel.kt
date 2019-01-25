package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class MovieCreditRemoteModel(
        @Json(name = "id") val id: Int,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "vote_average") val voteAverage: Float
)