package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.PersonLocalModel

@Dao
interface PersonsDao {

    @Query("SELECT * FROM Persons WHERE id = :id")
    fun getById(id: Int): Maybe<PersonLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonLocalModel)
}