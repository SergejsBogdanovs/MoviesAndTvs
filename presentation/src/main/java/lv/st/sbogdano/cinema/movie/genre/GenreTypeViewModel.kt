package lv.st.sbogdano.cinema.movie.genre

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.entity.GenreType
import lv.st.sbogdano.domain.interactor.GenreTypeGetAllUseCase

class GenreTypeViewModel(context: Context, private val genreTypeGetAllUseCase: GenreTypeGetAllUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    val loading = ObservableBoolean()
    val result = ObservableArrayList<GenreType>()
    val empty = ObservableBoolean()
    val error = ObservableField<String>()

    fun loadGenreTypeList() = addDisposable(getAllGenreTypes())

    private fun getAllGenreTypes(): Disposable {
        return genreTypeGetAllUseCase.execute()
                .subscribeWith(object : DisposableObserver<List<GenreType>>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(t: List<GenreType>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t)
                        empty.set(t.isEmpty())
                    }

                    override fun onError(t: Throwable) {
                        loading.set(false)
                        error.set(t.localizedMessage ?: t.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                        // no-op
                    }
                })
    }
}