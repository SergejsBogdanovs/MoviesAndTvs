package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.MissingUseCaseParameterException
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.gateway.Gateway

class TvByTypeGetAllUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<String, Boolean>, List<Tv>>(schedulers) {

    override fun buildObservable(params: Pair<String, Boolean>?): Observable<List<Tv>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val (type, refresh) = params
        return gateway.getTvByType(type, refresh)
    }
}