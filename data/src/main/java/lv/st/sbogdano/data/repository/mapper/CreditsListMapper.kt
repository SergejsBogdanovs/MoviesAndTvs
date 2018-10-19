package lv.st.sbogdano.data.repository.mapper

import lv.st.sbogdano.data.local.model.CreditLocalModel
import lv.st.sbogdano.data.remote.model.CreditRemoteModel

class CreditsListMapper {

    private fun toLocal(credit: CreditRemoteModel) =
            CreditLocalModel(credit.id, credit.name, credit.character, credit.profilePath)

    fun toLocal(items: List<CreditRemoteModel>) = items.map { toLocal(it) }
}