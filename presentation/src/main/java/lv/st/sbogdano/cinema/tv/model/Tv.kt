package lv.st.sbogdano.cinema.tv.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv(
        var id: Int,
        var posterPath: String?,
        var overview: String,
        var firstAirDate: String,
        var name: String,
        var popularity: Float,
        var voteCount: Int,
        var voteAverage: Float
) : Parcelable
