package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.gateway.Gateway

class MovieGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Int, Movie>(schedulers) {

    override fun buildObservable(params: Int?): Observable<Movie> {
        return gateway.getMovieById(params!!)
    }
}