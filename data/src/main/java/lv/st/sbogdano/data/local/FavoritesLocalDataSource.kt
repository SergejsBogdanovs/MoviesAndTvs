package lv.st.sbogdano.data.local

import io.reactivex.Completable
import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.FavoritesDao
import lv.st.sbogdano.data.local.model.FavoriteLocalModel

class FavoritesLocalDataSource(private val favoritesDao: FavoritesDao) {

    fun addToFavorites(favoriteLocalModel: FavoriteLocalModel): Completable = favoritesDao.insertToFavorites(favoriteLocalModel)

    fun getAll(type: String): Observable<List<FavoriteLocalModel>> = favoritesDao.getAll(type).toObservable()

    fun getById(id: Int?): Observable<FavoriteLocalModel> = favoritesDao.getById(id).toObservable()
}