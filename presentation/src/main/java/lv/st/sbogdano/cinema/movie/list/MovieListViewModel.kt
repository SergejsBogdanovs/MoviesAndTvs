package lv.st.sbogdano.cinema.movie.list

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.movie.list.mapper.MovieMapper
import lv.st.sbogdano.cinema.movie.list.model.MovieModel
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase

class MovieListViewModel(context: Context,
                         private val moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper()

    val loading = ObservableBoolean()
    val result = ObservableArrayList<MovieModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    private var movieType = ""

    fun loadMovieList(type: String, refresh: Boolean = false) {
        this.movieType = type
        addDisposable(findMoviesByType(type, refresh))
    }

    fun refresh() = loadMovieList(movieType, true)

    private fun findMoviesByType(type: String, refresh: Boolean): Disposable {
        val params = Pair(type, refresh)
        return moviesByTypeGetAllUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<Movie>>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<Movie>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t.map { mapper.toModel(it) })
                        empty.set(t.isEmpty())
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