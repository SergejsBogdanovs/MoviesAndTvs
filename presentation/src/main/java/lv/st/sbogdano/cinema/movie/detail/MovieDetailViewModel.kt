package lv.st.sbogdano.cinema.movie.detail

import android.app.Application
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.movie.detail.mapper.MovieMapper
import lv.st.sbogdano.cinema.movie.detail.model.MovieModel
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase

class MovieDetailViewModel(context: Context,
                           private val movieGetByIdUseCase: MovieGetByIdUseCase)
//                           private val videoGetByIdUseCase: VideoGetByIdUseCase,
//                           private val reviewGetByIdUseCase: ReviewGetByIdUseCase,
//                           private val creditsGetByIdUseCase: CreditsGetByIdUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper()

    val loading = ObservableBoolean()
    val movie = ObservableField<MovieModel>()
    val error = ObservableField<String>()

    fun loadMovieDetail(id: Int) = addDisposable(getMovieById(id))

    private fun getMovieById(id: Int): Disposable {
        return movieGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<Movie>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: Movie) {
                        loading.set(false)
                        movie.set(mapper.toModel(result))
                    }

                    override fun onError(e: Throwable) {
                        loading.set(false)
                        error.set(e.localizedMessage ?: e.message ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }

                })
    }
}