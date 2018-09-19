package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.GenreTypeLocalModel
import lv.st.sbogdano.data.remote.model.GenreTypeRemoteModel

class GenreTypeMapper {

    fun toLocal(genreType: GenreTypeRemoteModel) = GenreTypeLocalModel(genreType.id, genreType.name)

    fun toLocal(items: List<GenreTypeRemoteModel>) = items.map { toLocal(it) }
}