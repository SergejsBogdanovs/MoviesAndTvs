package lv.st.sbogdano.cinema.tv.list.mapper

import lv.st.sbogdano.cinema.tv.list.model.TvListModel
import lv.st.sbogdano.domain.entity.Tv

class TvMapper {
    fun toModel(tv: Tv): TvListModel {
        return TvListModel(tv.id, tv.posterPath)
    }
}