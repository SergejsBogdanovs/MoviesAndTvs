package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.PersonLocalModel
import lv.st.sbogdano.data.remote.model.PersonRemoteModel

class PersonMapper {

    fun toLocal(it: PersonRemoteModel) = PersonLocalModel(
            it.id,
            it.name,
            it.birthDay,
            it.knownForDepartment,
            it.gender,
            it.biography,
            it.placeOfBirth,
            it.profilePath,
            it.homePage
    )
}