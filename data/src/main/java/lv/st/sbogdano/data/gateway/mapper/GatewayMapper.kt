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
            movieLocalModel.voteAverage,
            movieLocalModel.type
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

    fun toLocalModel(favoriteDomainModel: FavoriteDomainModel) = FavoriteLocalModel(
            favoriteDomainModel.id,
            favoriteDomainModel.posterPath,
            favoriteDomainModel.type)

    fun toDomainModel(favoriteLocalModel: FavoriteLocalModel) = FavoriteDomainModel(
            favoriteLocalModel.id,
            favoriteLocalModel.posterPath,
            favoriteLocalModel.type
    )
}