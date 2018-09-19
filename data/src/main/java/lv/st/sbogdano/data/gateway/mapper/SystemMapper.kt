package lv.st.sbogdano.data.gateway.mapper

import lv.st.sbogdano.data.local.model.GenreTypeLocalModel
import lv.st.sbogdano.domain.entity.GenreType

class SystemMapper {

    fun toEntity(type: GenreTypeLocalModel) = GenreType(type.id, type.name)
}