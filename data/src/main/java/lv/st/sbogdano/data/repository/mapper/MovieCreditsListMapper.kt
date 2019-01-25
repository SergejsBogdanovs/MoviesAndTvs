package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.MovieCreditLocalModel
import lv.st.sbogdano.data.remote.model.MovieCreditRemoteModel

class MovieCreditsListMapper {

    private fun toLocal(movieCreditRemoteModel: MovieCreditRemoteModel) = MovieCreditLocalModel(
            movieCreditRemoteModel.id,
            movieCreditRemoteModel.posterPath,
            movieCreditRemoteModel.voteAverage
    )

    fun toLocal(items: List<MovieCreditRemoteModel>) = items.map { toLocal(it) }
}