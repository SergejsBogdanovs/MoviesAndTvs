package lv.st.sbogdano.cinema.movie.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Movie
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieDetailViewModel(
        context: Context,
        private val movieGetByIdUseCase: MovieGetByIdObservableUseCase,
        private val addToFavoritesUseCase: AddToFavoritesUseCase,
        creditsGetByIdUseCase: CreditsGetByIdObservableUseCase,
        videosGetByIdUseCase: VideosGetByIdObservableUseCase,
        reviewGetByIdUseCase: ReviewGetByIdObservableUseCase,
        getFavoriteByIdUseCase: GetFavoriteByIdObservableUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application,
        creditsGetByIdUseCase,
        videosGetByIdUseCase,
        reviewGetByIdUseCase,
        getFavoriteByIdUseCase
) {

    val movie = ObservableField<Movie>()

    fun loadMovieDetail(id: Int) {
        addDisposable(checkIsFavorite(id))
        addDisposable(getMovieById(id))
    }

    fun addItemToFavorites(path: String) {
        if (!isFavorite.get()) {
            addDisposable(addToFavorites(movie.get()!!, path))
        } else {
            isInserted.value = false
        }
    }

    private fun getMovieById(id: Int): Disposable {
        return movieGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<MovieDomainModel>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: MovieDomainModel) {
                        loading.set(false)
                        movie.set(movieMapper.toModel(result))
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

    private fun addToFavorites(movie: Movie, path: String): Disposable {
        val favoriteDomainModel = movieMapper.toDomainModel(movie, path)

        return addToFavoritesUseCase.execute(favoriteDomainModel)
                .subscribeWith(object : DisposableCompletableObserver() {

                    override fun onComplete() {
                        isFavorite.set(true)
                        isInserted.value = true
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }
                })
    }
}