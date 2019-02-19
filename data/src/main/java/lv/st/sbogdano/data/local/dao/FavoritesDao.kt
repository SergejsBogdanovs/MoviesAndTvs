package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.FavoriteLocalModel

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorites(favoriteLocalModel: FavoriteLocalModel): Completable

    @Query("SELECT * FROM Favorites WHERE type = :type")
    fun getAll(type: String): Maybe<List<FavoriteLocalModel>>

    @Query("SELECT * FROM Favorites WHERE id = :id")
    fun getById(id: Int?): Maybe<FavoriteLocalModel>
}