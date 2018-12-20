package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.FavoriteLocalModel

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorites(favoriteLocalModel: FavoriteLocalModel): Maybe<Long>

    @Query("SELECT * FROM Favorites WHERE type = :type")
    fun getAll(type: String): Maybe<List<FavoriteLocalModel>>
}