package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.CreditDomainModel

class CreditsGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<Int, String>, List<CreditDomainModel>>(schedulers) {
    override fun buildObservable(params: Pair<Int, String>?): Observable<List<CreditDomainModel>> {
        return gateway.getCreditsById(params!!)
    }
}