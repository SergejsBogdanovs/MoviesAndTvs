package lv.st.sbogdano.cinema.movie.detail.model

data class MovieModel(
    val id: Int,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float
)