package lv.st.sbogdano.data.repository

import io.reactivex.Observable
import lv.st.sbogdano.data.local.MovieCreditsLocalDataSource
import lv.st.sbogdano.data.local.model.MovieCreditLocalModel
import lv.st.sbogdano.data.remote.MovieCreditsRemoteDataSource
import lv.st.sbogdano.data.repository.mapper.MovieCreditsListMapper

class MovieCreditsRepository(
        private val movieCreditsLocalDataSource: MovieCreditsLocalDataSource,
        private val movieCreditsRemoteDataSource: MovieCreditsRemoteDataSource,
        private val movieCreditsListMapper: MovieCreditsListMapper
) {

    fun getById(id: Int): Observable<List<MovieCreditLocalModel>> {

        val local = movieCreditsLocalDataSource.getById(id)
                .filter { !it.isEmpty() }

        val remote = movieCreditsRemoteDataSource.getById(id)
                .map { it.filter { element -> !element.posterPath.isNullOrBlank() } }
                .map { it.sortedWith(compareByDescending { movieCreditRemoteModel -> movieCreditRemoteModel.voteAverage }) }
                .map { it.take(8) }
                .map { movieCreditsListMapper.toLocal(it) }
                .doOnNext { movieCreditsLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }
}