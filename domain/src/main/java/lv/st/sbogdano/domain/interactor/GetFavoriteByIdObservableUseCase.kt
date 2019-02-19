package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class GetFavoriteByIdObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Int, FavoriteDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<FavoriteDomainModel> {
        return gateway.getFavoriteById(params)
    }
}