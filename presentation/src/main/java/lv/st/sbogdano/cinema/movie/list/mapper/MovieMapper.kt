package lv.st.sbogdano.cinema.movie.list.mapper

import lv.st.sbogdano.cinema.movie.list.model.MovieModel
import lv.st.sbogdano.domain.entity.Movie

class MovieMapper {

    fun toModel(movie: Movie): MovieModel {
        return MovieModel(movie.id, movie.posterPath)
    }
}