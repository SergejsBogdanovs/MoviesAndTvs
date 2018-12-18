package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.FavoritesLocalDataSource
import lv.st.sbogdano.data.local.model.MovieLocalModel

class FavoritesRepository(private val favoritesLocalDataSource: FavoritesLocalDataSource) {

    fun addToFavorites(movieLocalModel: MovieLocalModel): Observable<Long> {
        return Observable.just(favoritesLocalDataSource.addToFavorites(movieLocalModel))
    }

}