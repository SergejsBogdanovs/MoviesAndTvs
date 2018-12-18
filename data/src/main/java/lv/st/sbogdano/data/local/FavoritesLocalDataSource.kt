package lv.st.sbogdano.data.local

import lv.st.sbogdano.data.local.dao.FavoritesDao
import lv.st.sbogdano.data.local.model.MovieLocalModel

class FavoritesLocalDataSource(private val favoritesDao: FavoritesDao) {

    fun addToFavorites(movieLocalModel: MovieLocalModel): Long? = favoritesDao.insertToFavorites(movieLocalModel)
}