package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.ReviewsLocalDataSource
import lv.st.sbogdano.data.local.model.ReviewLocalModel
import lv.st.sbogdano.data.remote.ReviewsRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.ReviewsListMapper

class ReviewsRepository(
    private val reviewsLocalDataSource: ReviewsLocalDataSource,
    private val reviewsRemoteDataSource: ReviewsRemoteDataSource,
    private val reviewsListMapper: ReviewsListMapper
) {

    fun getAllById(params: Pair<Int, String>): Observable<List<ReviewLocalModel>> {

        val (id, path) = params

        val local = reviewsLocalDataSource.getAllById(id)
                .filter { !it.isEmpty() }

        val remote = reviewsRemoteDataSource.getAllById(id, path)
                .map { reviewsListMapper.toLocal(it) }
                .doOnNext { reviewsLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}