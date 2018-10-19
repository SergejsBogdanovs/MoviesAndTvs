package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.gateway.Gateway

class CreditsGetByIdUseCase(schedulers: Schedulers,
                            private val gateway: Gateway)
    : UseCase<Int, List<Credit>>(schedulers) {

    override fun buildObservable(params: Int?): Observable<List<Credit>> {
        return gateway.getCreditsById(params!!)
    }

}