package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.MissingUseCaseParameterException
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.gateway.Gateway

class MoviesByTypeGetAllUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<String, Boolean>, List<Movie>>(schedulers) {

    override fun buildObservable(params: Pair<String, Boolean>?): Observable<List<Movie>> {
        if (params == null) throw MissingUseCaseParameterException(javaClass)
        val (type, refresh) = params
        return gateway.getMoviesByType(type, refresh)
    }
}