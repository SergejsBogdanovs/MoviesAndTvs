package lv.st.sbogdano.data.remote

import io.reactivex.Observable
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.remote.model.ReviewRemoteModel

class ReviewsRemoteDataSource(private val cinemaService: CinemaService) {

    fun getAllById(id: Int): Observable<List<ReviewRemoteModel>> = cinemaService.getReviewsById(id)
}