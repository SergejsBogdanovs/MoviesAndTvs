package lv.st.sbogdano.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.MovieLocalModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE type = :type")
    fun getAll(type: String): Maybe<List<MovieLocalModel>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id: Int): Maybe<MovieLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: MovieLocalModel)

    @Query("DELETE FROM Movie WHERE type = :type")
    fun deleteByType(type: String)
}