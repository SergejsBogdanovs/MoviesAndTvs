package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.*
import lv.st.sbogdano.domain.entity.*

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

    fun toEntity(creditLocalModel: CreditLocalModel) = Credit(
            creditLocalModel.id,
            creditLocalModel.name,
            creditLocalModel.character,
            creditLocalModel.profilePath
    )

    fun toEntity(videoLocalModel: VideoLocalModel) = Video(
            videoLocalModel.id,
            videoLocalModel.key,
            videoLocalModel.name
    )

    fun toEntity(reviewLocalModel: ReviewLocalModel) = Review(
            reviewLocalModel.id,
            reviewLocalModel.author,
            reviewLocalModel.content
    )
}