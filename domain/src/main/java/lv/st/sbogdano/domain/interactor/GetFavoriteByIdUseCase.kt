package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class GetFavoriteByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Int, FavoriteDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<FavoriteDomainModel> {
        return gateway.getFavoriteById(params)
    }
}