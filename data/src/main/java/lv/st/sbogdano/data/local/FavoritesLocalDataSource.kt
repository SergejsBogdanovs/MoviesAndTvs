package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.FavoritesDao
import lv.st.sbogdano.data.local.model.FavoriteLocalModel

class FavoritesLocalDataSource(private val favoritesDao: FavoritesDao) {

    fun addToFavorites(favoriteLocalModel: FavoriteLocalModel): Observable<Long> =
            favoritesDao.insertToFavorites(favoriteLocalModel).toObservable()

    fun getAll(type: String): Observable<List<FavoriteLocalModel>> = favoritesDao.getAll(type).toObservable()
}