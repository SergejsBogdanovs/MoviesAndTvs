package lv.st.sbogdano.domain.model

data class CreditDomainModel(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?
)