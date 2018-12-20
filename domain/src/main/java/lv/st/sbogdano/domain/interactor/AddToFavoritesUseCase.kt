package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class AddToFavoritesUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : UseCase<FavoriteDomainModel, Long>(schedulers) {

    override fun buildObservable(params: FavoriteDomainModel?): Observable<Long> {
        return gateway.addToFavorites(params!!)
    }
}