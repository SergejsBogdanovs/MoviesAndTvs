package lv.st.sbogdano.cinema.movie.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Movie
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.MovieDomainModel

class MovieDetailViewModel(
    context: Context,
    private val movieGetByIdUseCase: MovieGetByIdUseCase,
    creditsGetByIdUseCase: CreditsGetByIdUseCase,
    videosGetByIdUseCase: VideosGetByIdUseCase,
    reviewGetByIdUseCase: ReviewGetByIdUseCase,
    addToFavoritesUseCase: AddToFavoritesUseCase,
    getFavoriteByIdUseCase: GetFavoriteByIdUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application,
        creditsGetByIdUseCase,
        videosGetByIdUseCase,
        reviewGetByIdUseCase,
        addToFavoritesUseCase,
        getFavoriteByIdUseCase
) {

    val movie = ObservableField<Movie>()

    fun loadMovieDetail(id: Int) {
        addDisposable(checkIsFavorite(id))
        addDisposable(getMovieById(id))
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
}