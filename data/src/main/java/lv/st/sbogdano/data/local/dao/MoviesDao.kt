package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.MovieLocalModel

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies WHERE type = :type")
    fun getAll(type: String): Maybe<List<MovieLocalModel>>

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun getById(id: Int): Maybe<MovieLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: MovieLocalModel)

    @Query("DELETE FROM Movies WHERE type = :type")
    fun deleteByType(type: String)
}