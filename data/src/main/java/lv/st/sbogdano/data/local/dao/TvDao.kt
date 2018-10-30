package lv.st.sbogdano.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.TvLocalModel

@Dao
interface TvDao {

    @Query("SELECT * FROM Tv WHERE type = :type")
    fun getAll(type: String): Maybe<List<TvLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tv: TvLocalModel)

    @Query("DELETE FROM Tv WHERE type = :type")
    fun deleteByType(type: String)
}