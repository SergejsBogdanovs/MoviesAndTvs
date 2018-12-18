package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.*
import lv.st.sbogdano.domain.model.*

class GatewayMapper {

    fun toDomainModel(movieLocalModel: MovieLocalModel) = MovieDomainModel(
            movieLocalModel.id,
            movieLocalModel.posterPath,
            movieLocalModel.overview,
            movieLocalModel.releaseDate,
            movieLocalModel.title,
            movieLocalModel.popularity,
            movieLocalModel.voteCount,
            movieLocalModel.voteAverage
    )

    fun toDomainModel(tvLocalModel: TvLocalModel) = TvDomainModel(
            tvLocalModel.id,
            tvLocalModel.posterPath,
            tvLocalModel.overview,
            tvLocalModel.firstAirDate,
            tvLocalModel.name,
            tvLocalModel.popularity,
            tvLocalModel.voteCount,
            tvLocalModel.voteAverage
    )

    fun toDomainModel(creditLocalModel: CreditLocalModel) = CreditDomainModel(
            creditLocalModel.id,
            creditLocalModel.name,
            creditLocalModel.character,
            creditLocalModel.profilePath
    )

    fun toDomainModel(videoLocalModel: VideoLocalModel) = VideoDomainModel(
            videoLocalModel.id,
            videoLocalModel.key,
            videoLocalModel.name
    )

    fun toDomainModel(reviewLocalModel: ReviewLocalModel) = ReviewDomainModel(
            reviewLocalModel.id,
            reviewLocalModel.author,
            reviewLocalModel.content
    )

    fun toLocalModel(params: Pair<MovieDomainModel, String>): MovieLocalModel {
        val (movieDomainModel, type) = params

        return MovieLocalModel(
                movieDomainModel.id,
                movieDomainModel.posterPath,
                movieDomainModel.overview,
                movieDomainModel.releaseDate,
                movieDomainModel.title,
                movieDomainModel.popularity,
                movieDomainModel.voteCount,
                movieDomainModel.voteAverage,
                type,
                true)
    }
}