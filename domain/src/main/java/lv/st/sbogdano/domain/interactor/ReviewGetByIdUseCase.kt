package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.entity.Review
import lv.st.sbogdano.domain.gateway.Gateway

class ReviewGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<Int, String>, List<Review>>(schedulers) {

    override fun buildObservable(params: Pair<Int, String>?): Observable<List<Review>> {
        return gateway.getReviewsById(params!!)
    }
}