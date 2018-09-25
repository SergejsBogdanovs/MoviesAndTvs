package lv.st.sbogdano.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.GenreTypeLocalModel

@Dao
interface GenreTypeDao {

    @Query("SELECT * FROM GenreType")
    fun getAll(): Maybe<List<GenreTypeLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg genreType: GenreTypeLocalModel)
}