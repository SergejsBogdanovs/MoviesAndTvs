package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.CreditDomainModel

class CreditsGetByIdObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Pair<Int, String>, List<CreditDomainModel>>(schedulers) {
    override fun buildObservable(params: Pair<Int, String>?): Observable<List<CreditDomainModel>> {
        return gateway.getCreditsById(params!!)
    }
}