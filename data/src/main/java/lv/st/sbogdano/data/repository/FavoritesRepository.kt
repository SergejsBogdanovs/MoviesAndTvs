package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.FavoritesLocalDataSource
import lv.st.sbogdano.data.local.model.FavoriteLocalModel

class FavoritesRepository(private val favoritesLocalDataSource: FavoritesLocalDataSource) {

    fun addToFavorites(favoriteLocalModel: FavoriteLocalModel): Observable<Long> =
            favoritesLocalDataSource.addToFavorites(favoriteLocalModel)

    fun getAll(type: String): Observable<List<FavoriteLocalModel>> =
            favoritesLocalDataSource.getAll(type)

}