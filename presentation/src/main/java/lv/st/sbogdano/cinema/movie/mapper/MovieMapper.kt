package lv.st.sbogdano.cinema.movie.mapper

import lv.st.sbogdano.cinema.basemodel.Movie
import lv.st.sbogdano.domain.model.FavoriteDomainModel
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieMapper {

    fun toModel(result: MovieDomainModel) = Movie(
            result.id,
            result.posterPath,
            result.overview,
            formatDate(result.releaseDate),
            result.title,
            result.popularity,
            result.voteCount,
            result.voteAverage,
            result.type)

    private fun formatDate(releaseDate: String): String {
        val yearMonth = releaseDate.substringBeforeLast("-")
        val year = yearMonth.substringBefore("-")
        val month = yearMonth.substringAfter("-")
        val day = releaseDate.substringAfterLast("-")
        return "$year $month $day"
    }

    fun toDomainModel(movie: Movie, type: String) = FavoriteDomainModel(
            movie.id,
            movie.posterPath,
            type)
}