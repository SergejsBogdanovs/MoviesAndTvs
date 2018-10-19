package lv.st.sbogdano.cinema.movie.detail.mapper

import lv.st.sbogdano.cinema.movie.detail.model.CreditModel
import lv.st.sbogdano.cinema.movie.detail.model.MovieModel
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie

class MovieMapper {

    fun toModel(result: Movie): MovieModel {
        return MovieModel(
                result.id,
                result.posterPath,
                result.overview,
                result.releaseDate,
                result.title,
                result.popularity,
                result.voteCount,
                result.voteAverage)
    }

    fun toModel(result: Credit): CreditModel {
        return CreditModel(
                result.id,
                result.name,
                result.character,
                result.profilePath)
    }
}