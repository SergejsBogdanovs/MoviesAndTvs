package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.PersonDomainModel

class PersonGetByIdUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
) : UseCase<Int, PersonDomainModel>(schedulers){

    override fun buildObservable(params: Int?): Observable<PersonDomainModel> {
        return gateway.getPersonById(params!!)
    }
}