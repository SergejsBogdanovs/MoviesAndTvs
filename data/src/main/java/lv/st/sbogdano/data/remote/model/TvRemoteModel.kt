package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class TvRemoteModel(
    @Json(name = "id") var id: Int,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") var overview: String,
    @Json(name = "first_air_date")var firstAirDate: String,
    @Json(name = "name") var name: String,
    @Json(name = "popularity") var popularity: Float,
    @Json(name = "vote_count") var voteCount: Int,
    @Json(name = "vote_average") var voteAverage: Float
)
