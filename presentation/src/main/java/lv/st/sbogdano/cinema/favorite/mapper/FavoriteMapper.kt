package lv.st.sbogdano.cinema.favorite.mapper

import lv.st.sbogdano.cinema.basemodel.Favorite
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class FavoriteMapper {

    fun toModel(result: FavoriteDomainModel) = Favorite(
            result.id,
            result.posterPath,
            result.type
    )
}