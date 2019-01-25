package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieCreditDomainModel

class MovieCreditsGetByPersonIdUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : UseCase<Int, List<MovieCreditDomainModel>>(schedulers) {

    override fun buildObservable(params: Int?): Observable<List<MovieCreditDomainModel>> {
        return gateway.getMovieCreditsById(params!!)
    }
}