package lv.st.sbogdano.cinema.tv.list.mapper

import lv.st.sbogdano.cinema.tv.list.model.TvModel
import lv.st.sbogdano.domain.entity.Tv

class TvMapper {
    fun toModel(tv: Tv): TvModel {
        return TvModel(tv.id, tv.posterPath)
    }
}