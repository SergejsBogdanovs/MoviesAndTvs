package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.TvLocalModel

@Dao
interface TvsDao {

    @Query("SELECT * FROM Tvs WHERE type = :type")
    fun getAll(type: String): Maybe<List<TvLocalModel>>

    @Query("SELECT * FROM Tvs WHERE id = :id")
    fun getById(id: Int): Maybe<TvLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tv: TvLocalModel)

    @Query("DELETE FROM Tvs WHERE type = :type")
    fun deleteByType(type: String)
}