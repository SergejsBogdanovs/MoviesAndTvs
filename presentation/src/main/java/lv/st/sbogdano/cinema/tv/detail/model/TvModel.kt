package lv.st.sbogdano.cinema.tv.detail.model

data class TvModel(
    val id: Int,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float
)