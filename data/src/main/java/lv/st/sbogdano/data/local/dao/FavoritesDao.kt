package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import lv.st.sbogdano.data.local.model.MovieLocalModel

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorites(movieLocalModel: MovieLocalModel): Long

}