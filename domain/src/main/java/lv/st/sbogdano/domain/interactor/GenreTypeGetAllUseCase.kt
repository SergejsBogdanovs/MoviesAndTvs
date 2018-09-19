package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.GenreType
import lv.st.sbogdano.domain.gateway.SystemGateway

class GenreTypeGetAllUseCase(schedulers: Schedulers,
                             private val systemGateWay: SystemGateway
) : UseCase<Void, List<GenreType>>(schedulers) {

    override fun buildObservable(params: Void?): Observable<List<GenreType>> {
        return systemGateWay.getGenreTypes()
    }

}