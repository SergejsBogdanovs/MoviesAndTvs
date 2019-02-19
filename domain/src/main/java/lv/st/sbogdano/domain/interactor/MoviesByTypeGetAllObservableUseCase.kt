package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.MissingUseCaseParameterException
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieDomainModel

class MoviesByTypeGetAllObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Pair<String, Boolean>, List<MovieDomainModel>>(schedulers) {

    override fun buildObservable(params: Pair<String, Boolean>?): Observable<List<MovieDomainModel>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val (type, refresh) = params
        return gateway.getMoviesByType(type, refresh)
    }
}