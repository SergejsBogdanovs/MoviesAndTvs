package lv.st.sbogdano.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.CreditLocalModel
@Dao
interface CreditsDao {

    @Query("SELECT * FROM Credits WHERE id = :id")
    fun getAllById(id: Int): Maybe<List<CreditLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg credit: CreditLocalModel)
}