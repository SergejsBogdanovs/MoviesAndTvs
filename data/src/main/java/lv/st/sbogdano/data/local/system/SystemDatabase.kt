package lv.st.sbogdano.data.local.system

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import lv.st.sbogdano.data.local.dao.GenreTypeDao
import lv.st.sbogdano.data.local.model.GenreTypeLocalModel

@Database(entities = [GenreTypeLocalModel::class], version = 1, exportSchema = false)
abstract class SystemDatabase : RoomDatabase() {

    abstract fun genreTypeDao(): GenreTypeDao

    companion object {
        fun newInstance(context: Context): SystemDatabase {
            return Room.databaseBuilder(context, SystemDatabase::class.java, "cinema.db").build()
        }
    }
}