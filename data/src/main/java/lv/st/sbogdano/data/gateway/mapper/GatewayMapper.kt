package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.MovieLocalModel
import lv.st.sbogdano.data.local.model.TvLocalModel
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Tv

class GatewayMapper {

    fun toEntity(movieLocalModel: MovieLocalModel) = Movie(
            movieLocalModel.id,
            movieLocalModel.posterPath,
            movieLocalModel.overview,
            movieLocalModel.releaseDate,
            movieLocalModel.title,
            movieLocalModel.popularity,
            movieLocalModel.voteCount,
            movieLocalModel.voteAverage
    )

    fun toEntity(tvLocalModel: TvLocalModel) = Tv(
            tvLocalModel.id,
            tvLocalModel.posterPath,
            tvLocalModel.overview,
            tvLocalModel.firstAirDate,
            tvLocalModel.name,
            tvLocalModel.popularity,
            tvLocalModel.voteCount,
            tvLocalModel.voteAverage
    )
}