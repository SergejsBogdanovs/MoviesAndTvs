package lv.st.sbogdano.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import lv.st.sbogdano.data.local.model.VideoLocalModel

@Dao
interface VideosDao {

    @Query("SELECT * FROM Videos where id = :id")
    fun getAllById(id: Int): Maybe<List<VideoLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg video: VideoLocalModel)
}