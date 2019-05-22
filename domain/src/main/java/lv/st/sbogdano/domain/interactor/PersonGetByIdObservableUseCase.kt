package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.PersonDomainModel

class PersonGetByIdObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Int, PersonDomainModel>(schedulers) {

    override fun buildObservable(params: Int?): Observable<PersonDomainModel> {
        return gateway.getPersonById(params!!)
    }
}