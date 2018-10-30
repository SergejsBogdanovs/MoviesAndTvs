package lv.st.sbogdano.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import lv.st.sbogdano.data.local.dao.CreditsDao
import lv.st.sbogdano.data.local.dao.MovieDao
import lv.st.sbogdano.data.local.dao.TvDao
import lv.st.sbogdano.data.local.dao.VideosDao
import lv.st.sbogdano.data.local.model.CreditLocalModel
import lv.st.sbogdano.data.local.model.MovieLocalModel
import lv.st.sbogdano.data.local.model.TvLocalModel
import lv.st.sbogdano.data.local.model.VideoLocalModel

@Database(entities = [
    MovieLocalModel::class,
    TvLocalModel::class,
    CreditLocalModel::class,
    VideoLocalModel::class], version = 1, exportSchema = false)
abstract class CinemaDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
    abstract fun creditsDao(): CreditsDao
    abstract fun videosDao(): VideosDao

    companion object {
        fun newInstance(context: Context): CinemaDatabase {
            return Room.databaseBuilder(context, CinemaDatabase::class.java, "cinema.db").build()
        }
    }
}