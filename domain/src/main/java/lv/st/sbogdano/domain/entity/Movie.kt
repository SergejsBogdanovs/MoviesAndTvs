package lv.st.sbogdano.domain.entity

data class Movie(
        val id: Int,
        val posterPath: String?,
        val overview: String,
        val releaseDate: String,
        val title: String,
        val popularity: Float,
        val voteCount: Int,
        val voteAverage: Float
)