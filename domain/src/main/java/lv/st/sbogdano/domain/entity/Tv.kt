package lv.st.sbogdano.domain.entity

data class Tv(
    var id: Int,
    var posterPath: String?,
    var overview: String,
    var firstAirDate: String,
    var name: String,
    var popularity: Float,
    var voteCount: Int,
    var voteAverage: Float
)