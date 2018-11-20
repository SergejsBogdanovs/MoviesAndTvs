package lv.st.sbogdano.cinema.movie.detail.mapper

import lv.st.sbogdano.cinema.tv.detail.model.TvModel
import lv.st.sbogdano.domain.entity.Tv

class TvMapper {

    fun toModel(result: Tv): TvModel {
        return TvModel(
                result.id,
                result.posterPath,
                result.overview,
                formatDate(result.firstAirDate),
                result.name,
                result.popularity,
                result.voteCount,
                result.voteAverage)
    }

    private fun formatDate(firstAirDate: String): String {
        val yearMonth = firstAirDate.substringBeforeLast("-")
        val year = yearMonth.substringBefore("-")
        val month = yearMonth.substringAfter("-")
        val day = firstAirDate.substringAfterLast("-")
        return "$year $month $day"
    }
}