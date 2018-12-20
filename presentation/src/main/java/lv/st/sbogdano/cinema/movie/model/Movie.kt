package lv.st.sbogdano.cinema.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val id: Int,
        val posterPath: String?,
        val overview: String,
        val releaseDate: String,
        val title: String,
        val popularity: Float,
        val voteCount: Int,
        val voteAverage: Float,
        val type: String
) : Parcelable