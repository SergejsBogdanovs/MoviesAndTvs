package lv.st.sbogdano.cinema.movie.detail.mapper

import lv.st.sbogdano.cinema.movie.detail.model.MovieModel
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
}