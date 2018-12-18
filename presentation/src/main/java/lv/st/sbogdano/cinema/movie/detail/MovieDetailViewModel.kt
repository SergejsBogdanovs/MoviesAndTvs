package lv.st.sbogdano.cinema.movie.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.internal.util.SingleLiveData
import lv.st.sbogdano.cinema.movie.mapper.MovieMapper
import lv.st.sbogdano.cinema.movie.model.Movie
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.CreditDomainModel
import lv.st.sbogdano.domain.model.MovieDomainModel
import lv.st.sbogdano.domain.model.ReviewDomainModel
import lv.st.sbogdano.domain.model.VideoDomainModel

class MovieDetailViewModel(
    context: Context,
    private val movieGetByIdUseCase: MovieGetByIdUseCase,
    private val creditsGetByIdUseCase: CreditsGetByIdUseCase,
    private val videosGetByIdUseCase: VideosGetByIdUseCase,
    private val reviewGetByIdUseCase: ReviewGetByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper()
    private val path = "movie"
    private val _isInserted = SingleLiveData<Boolean>()
    val isInserted = _isInserted

    val loading = ObservableBoolean()
    val movie = ObservableField<Movie>()
    val credits = ObservableArrayList<CreditDomainModel>()
    val reviews = ObservableArrayList<ReviewDomainModel>()
    val video = ObservableField<VideoDomainModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadMovieDetail(id: Int) = addDisposable(getMovieById(id))
    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))
    fun loadVideos(id: Int) = addDisposable(getVideosById(id))
    fun loadReviews(id: Int) = addDisposable(getReviewsById(id))
    fun addMovieToFavorites(movie: Movie) = addDisposable(addToFavorites(movie))

    private fun getMovieById(id: Int): Disposable {
        return movieGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<MovieDomainModel>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: MovieDomainModel) {
                        loading.set(false)
                        movie.set(mapper.toModel(result))
                    }

                    override fun onError(e: Throwable) {
                        loading.set(false)
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

    private fun getCreditsById(id: Int): Disposable {
        val params = Pair(id, path)
        return creditsGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<CreditDomainModel>>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<CreditDomainModel>) {
                        loading.set(false)
                        credits.clear()
                        credits.addAll(result)
                        empty.set(result.isEmpty())
                    }

                    override fun onError(e: Throwable) {
                        loading.set(false)
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

    private fun getVideosById(id: Int): Disposable {
        val params = Pair(id, path)
        return videosGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<VideoDomainModel>>() {
                    override fun onNext(result: List<VideoDomainModel>) {
                        video.set(result.first())
                        empty.set(result.isEmpty())
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

    private fun getReviewsById(id: Int): Disposable {
        val params = Pair(id, path)
        return reviewGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<ReviewDomainModel>>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<ReviewDomainModel>) {
                        loading.set(false)
                        reviews.clear()
                        reviews.addAll(result)
                        empty.set(result.isEmpty())
                    }

                    override fun onError(e: Throwable) {
                        loading.set(false)
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

    private fun addToFavorites(movie: Movie): Disposable {
        val movieDomainModel = mapper.toDomainModel(movie)
        val params = Pair(movieDomainModel, path)
        return addToFavoritesUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: Long) {
                        isInserted.value = true
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }
                })
    }
}