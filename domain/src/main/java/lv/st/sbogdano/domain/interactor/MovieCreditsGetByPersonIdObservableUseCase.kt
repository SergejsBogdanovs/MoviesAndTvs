package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieCreditDomainModel

class MovieCreditsGetByPersonIdObservableUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : ObservableUseCase<Int, List<MovieCreditDomainModel>>(schedulers) {

    override fun buildObservable(params: Int?): Observable<List<MovieCreditDomainModel>> {
        return gateway.getMovieCreditsById(params!!)
    }
}