package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class FavoritesByTypeGetAllObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<String, List<FavoriteDomainModel>>(schedulers) {

    override fun buildObservable(params: String?): Observable<List<FavoriteDomainModel>> {
        return gateway.getFavoritesByType(params!!)
    }
}