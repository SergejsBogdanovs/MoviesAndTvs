package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.usecases.ObservableUseCase
import lv.st.sbogdano.domain.model.ReviewDomainModel
import lv.st.sbogdano.domain.gateway.Gateway

class ReviewGetByIdObservableUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : ObservableUseCase<Pair<Int, String>, List<ReviewDomainModel>>(schedulers) {

    override fun buildObservable(params: Pair<Int, String>?): Observable<List<ReviewDomainModel>> {
        return gateway.getReviewsById(params!!)
    }
}