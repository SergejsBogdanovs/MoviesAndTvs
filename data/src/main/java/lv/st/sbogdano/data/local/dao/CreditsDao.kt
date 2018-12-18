package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.CreditLocalModel
@Dao
interface CreditsDao {

    @Query("SELECT * FROM Credits WHERE id = :id")
    fun getAllById(id: Int): Maybe<List<CreditLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg credit: CreditLocalModel)
}