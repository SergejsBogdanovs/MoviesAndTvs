package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Int, MovieDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<MovieDomainModel> {
        return gateway.getMovieById(params!!)
    }
}