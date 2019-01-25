package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.MovieCreditLocalModel

@Dao
interface MovieCreditsDao {

    @Query("SELECT * FROM MovieCredits WHERE id = :id")
    fun getById(id: Int): Maybe<List<MovieCreditLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movieCredits: MovieCreditLocalModel)
}