package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.UseCase
import lv.st.sbogdano.domain.model.VideoDomainModel
import lv.st.sbogdano.domain.gateway.Gateway

class VideosGetByIdUseCase(
    schedulers: Schedulers,
    private val gateway: Gateway
) : UseCase<Pair<Int, String>, List<VideoDomainModel>>(schedulers) {

    override fun buildObservable(params: Pair<Int, String>?): Observable<List<VideoDomainModel>> {
        return gateway.getVideosById(params!!)
    }
}