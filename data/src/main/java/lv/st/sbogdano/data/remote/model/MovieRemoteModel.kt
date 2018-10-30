package lv.st.sbogdano.data.remote.model

import com.squareup.moshi.Json

data class MovieRemoteModel(
    @Json(name = "id") var id: Int,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") var overview: String,
    @Json(name = "release_date")var releaseDate: String,
    @Json(name = "title") var title: String,
    @Json(name = "popularity") var popularity: Float,
    @Json(name = "vote_count") var voteCount: Int,
    @Json(name = "vote_average") var voteAverage: Float
)