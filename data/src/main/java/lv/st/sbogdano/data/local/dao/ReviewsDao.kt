package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.ReviewLocalModel

@Dao
interface ReviewsDao {

    @Query("SELECT * FROM Reviews WHERE id = :id")
    fun getAllById(id: Int): Maybe<List<ReviewLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg review: ReviewLocalModel)
}