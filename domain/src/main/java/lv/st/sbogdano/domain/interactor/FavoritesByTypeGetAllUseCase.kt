package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class FavoritesByTypeGetAllUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<String, List<FavoriteDomainModel>>(schedulers) {

    override fun buildObservable(params: String?): Observable<List<FavoriteDomainModel>> {
        return gateway.getFavoritesByType(params!!)
    }
}