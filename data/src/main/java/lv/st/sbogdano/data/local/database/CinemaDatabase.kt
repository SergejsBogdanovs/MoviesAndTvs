package lv.st.sbogdano.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lv.st.sbogdano.data.local.dao.*
import lv.st.sbogdano.data.local.model.*

@Database(entities = [
    MovieLocalModel::class,
    TvLocalModel::class,
    CreditLocalModel::class,
    VideoLocalModel::class,
    ReviewLocalModel::class,
    FavoriteLocalModel::class], version = 1, exportSchema = false)
abstract class CinemaDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvsDao(): TvsDao
    abstract fun creditsDao(): CreditsDao
    abstract fun videosDao(): VideosDao
    abstract fun reviewsDao(): ReviewsDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        fun newInstance(context: Context): CinemaDatabase {
            return Room.databaseBuilder(context, CinemaDatabase::class.java, "cinema.db").build()
        }
    }
}