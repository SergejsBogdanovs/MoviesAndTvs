package lv.st.sbogdano.cinema.movie.detail.mapper

import lv.st.sbogdano.cinema.movie.detail.model.MovieModel
import lv.st.sbogdano.domain.entity.Movie

class MovieMapper {

    fun toModel(result: Movie): MovieModel {
        return MovieModel(
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
}