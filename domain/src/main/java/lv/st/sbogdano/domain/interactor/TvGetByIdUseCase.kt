package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.gateway.Gateway

class TvGetByIdUseCase(schedulers: Schedulers,
                       private val gateway: Gateway
) : UseCase<Int, Tv>(schedulers) {

    override fun buildObservable(params: Int?): Observable<Tv> {
        return gateway.getTvById(params!!)
    }
}