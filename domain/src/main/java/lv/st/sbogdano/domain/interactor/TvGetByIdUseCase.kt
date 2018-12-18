package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.model.TvDomainModel
import lv.st.sbogdano.domain.gateway.Gateway

class TvGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Int, TvDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<TvDomainModel> {
        return gateway.getTvById(params!!)
    }
}