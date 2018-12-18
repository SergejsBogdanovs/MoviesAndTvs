package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.MovieDomainModel

class AddToFavoritesUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : UseCase<Pair<MovieDomainModel, String>, Long>(schedulers) {

    override fun buildObservable(params: Pair<MovieDomainModel, String>?): Observable<Long> {
        return gateway.addToFavorites(params!!)
    }
}