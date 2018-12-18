package lv.st.sbogdano.cinema.movie.mapper

import lv.st.sbogdano.cinema.movie.model.Movie
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieMapper {

    fun toModel(result: MovieDomainModel): Movie {
        return Movie(
                result.id,
                result.posterPath,
                result.overview,
                formatDate(result.releaseDate),
                result.title,
                result.popularity,
                result.voteCount,
                result.voteAverage)
    }

    private fun formatDate(releaseDate: String): String {
        val yearMonth = releaseDate.substringBeforeLast("-")
        val year = yearMonth.substringBefore("-")
        val month = yearMonth.substringAfter("-")
        val day = releaseDate.substringAfterLast("-")
        return "$year $month $day"
    }

    fun toDomainModel(movie: Movie): MovieDomainModel = MovieDomainModel(
            movie.id,
            movie.posterPath,
            movie.overview,
            movie.releaseDate,
            movie.title,
            movie.popularity,
            movie.voteCount,
            movie.voteAverage)

}