package lv.st.sbogdano.cinema.movie.list.mapper

import lv.st.sbogdano.cinema.movie.list.model.MovieListModel
import lv.st.sbogdano.domain.entity.Movie

class MovieListMapper {

    fun toModel(movie: Movie): MovieListModel {
        return MovieListModel(movie.id, movie.posterPath)
    }
}