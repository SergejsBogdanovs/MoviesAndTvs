package lv.st.sbogdano.data.local

import io.reactivex.Observable
import lv.st.sbogdano.data.local.dao.ReviewsDao
import lv.st.sbogdano.data.local.model.ReviewLocalModel

class ReviewsLocalDataSource(private val reviewsDao: ReviewsDao) {

    fun getAllById(id: Int): Observable<List<ReviewLocalModel>> = reviewsDao.getAllById(id).toObservable()

    fun insertAll(reviews: List<ReviewLocalModel>) = reviewsDao.insertAll(*reviews.toTypedArray())

}