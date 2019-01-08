package lv.st.sbogdano.cinema.basemodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class BaseModel

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
) : BaseModel(), Parcelable

@Parcelize
data class Tv(
    val id: Int,
    val posterPath: String?,
    val overview: String,
    val firstAirDate: String,
    val name: String,
    val popularity: Float,
    val voteCount: Int,
    val voteAverage: Float
) : BaseModel(), Parcelable
