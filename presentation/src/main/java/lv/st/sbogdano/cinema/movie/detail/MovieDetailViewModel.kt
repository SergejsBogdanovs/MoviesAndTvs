package lv.st.sbogdano.cinema.movie.detail

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase

class MovieDetailViewModel(context: Context,
                           private val movieGetByIdUseCase: MovieGetByIdUseCase,
                           private val creditsGetByIdUseCase: CreditsGetByIdUseCase)
//                           private val videoGetByIdUseCase: VideoGetByIdUseCase,
//                           private val reviewGetByIdUseCase: ReviewGetByIdUseCase,
//                           )
    : BaseAndroidViewModel(context.applicationContext as Application) {

    //private val mapper = MovieMapper()

    val loading = ObservableBoolean()
    val movie = ObservableField<Movie>()
    val credits = ObservableArrayList<Credit>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadMovieDetail(id: Int) = addDisposable(getMovieById(id))

    private fun getMovieById(id: Int): Disposable {
        return movieGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<Movie>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: Movie) {
                        loading.set(false)
                        movie.set(result)
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

    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))

    private fun getCreditsById(id: Int): Disposable {
        return creditsGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<List<Credit>>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(t: List<Credit>) {
                        loading.set(false)
                        credits.clear()
                        credits.addAll(t)
                        empty.set(t.isEmpty())
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