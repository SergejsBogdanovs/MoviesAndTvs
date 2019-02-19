package lv.st.sbogdano.domain.interactor

import io.reactivex.Completable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel
import lv.st.sbogdano.domain.usecases.CompletableUseCase

class AddToFavoritesUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : CompletableUseCase<FavoriteDomainModel, Long>(schedulers) {

    override fun buildCompletable(params: FavoriteDomainModel?): Completable {
        return gateway.addToFavorites(params!!)
    }
}