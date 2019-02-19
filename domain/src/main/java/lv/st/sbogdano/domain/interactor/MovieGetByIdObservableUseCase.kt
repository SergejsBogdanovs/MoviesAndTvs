package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieGetByIdObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Int, MovieDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<MovieDomainModel> {
        return gateway.getMovieById(params!!)
    }
}