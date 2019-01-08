package lv.st.sbogdano.domain.model

data class MovieDomainModel(
    val id: Int,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float,
    val type: String
)