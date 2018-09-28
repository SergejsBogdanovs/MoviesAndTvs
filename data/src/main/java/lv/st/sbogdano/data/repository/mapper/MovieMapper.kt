package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.MovieLocalModel
import lv.st.sbogdano.data.remote.model.MovieRemoteModel

class MovieMapper {

    private fun toLocal(movie: MovieRemoteModel, type: String) = MovieLocalModel(
            movie.id,
            movie.posterPath,
            movie.overview,
            movie.releaseDate,
            movie.title,
            movie.popularity,
            movie.voteCount,
            movie.voteAverage,
            type
    )

    fun toLocal(items: List<MovieRemoteModel>, type: String) = items.map { toLocal(it, type) }
}