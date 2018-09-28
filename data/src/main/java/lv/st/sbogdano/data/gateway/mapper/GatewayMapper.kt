package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.MovieLocalModel
import lv.st.sbogdano.domain.entity.Movie

class GatewayMapper {

    fun toEntity(movieLocalModel: MovieLocalModel) = Movie(
            movieLocalModel.id,
            movieLocalModel.posterPath,
            movieLocalModel.overview,
            movieLocalModel.releaseDate,
            movieLocalModel.title,
            movieLocalModel.popularity,
            movieLocalModel.voteCount,
            movieLocalModel.voteAverage)
}