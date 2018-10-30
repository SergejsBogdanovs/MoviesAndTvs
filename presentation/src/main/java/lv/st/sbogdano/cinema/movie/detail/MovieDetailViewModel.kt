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
import lv.st.sbogdano.cinema.movie.detail.mapper.MovieMapper
import lv.st.sbogdano.cinema.movie.detail.model.MovieModel
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Video
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase

class MovieDetailViewModel(
        context: Context,
        private val movieGetByIdUseCase: MovieGetByIdUseCase,
        private val creditsGetByIdUseCase: CreditsGetByIdUseCase,
        private val videosGetByIdUseCase: VideosGetByIdUseCase
)
//
//                           private val reviewGetByIdUseCase: ReviewGetByIdUseCase,
//                           )
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper()

    val loading = ObservableBoolean()
    val movie = ObservableField<MovieModel>()
    val credits = ObservableArrayList<Credit>()
    val videos = ObservableArrayList<Video>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadMovieDetail(id: Int) = addDisposable(getMovieById(id))
    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))
    fun loadVideos(id: Int) = addDisposable(getVideosById(id))

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
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

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

    private fun getVideosById(id: Int): Disposable {
        return videosGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<List<Video>>() {
                    override fun onNext(t: List<Video>) {
                        videos.clear()
                        videos.addAll(t)
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