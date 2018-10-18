package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.TvLocalModel
import lv.st.sbogdano.data.remote.model.TvRemoteModel

class TvListMapper {

    private fun toLocal(tv: TvRemoteModel, type: String) = TvLocalModel(
            tv.id,
            tv.posterPath,
            tv.overview,
            tv.firstAirDate,
            tv.name,
            tv.popularity,
            tv.voteCount,
            tv.voteAverage,
            type
    )

    fun toLocal(items: List<TvRemoteModel>, type: String) = items.map { toLocal(it, type) }
}