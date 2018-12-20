package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.MissingUseCaseParameterException
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.TvDomainModel

class TvsByTypeGetAllUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<String, Boolean>, List<TvDomainModel>>(schedulers) {

    override fun buildObservable(params: Pair<String, Boolean>?): Observable<List<TvDomainModel>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val (type, refresh) = params
        return gateway.getTvsByType(type, refresh)
    }
}