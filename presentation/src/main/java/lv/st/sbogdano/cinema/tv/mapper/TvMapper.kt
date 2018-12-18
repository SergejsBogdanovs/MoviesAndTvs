package lv.st.sbogdano.cinema.tv.mapper

import lv.st.sbogdano.cinema.tv.model.Tv
import lv.st.sbogdano.domain.model.TvDomainModel

class TvMapper {

    fun toModel(result: TvDomainModel): Tv {
        return Tv(
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