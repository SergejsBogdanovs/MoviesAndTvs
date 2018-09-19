package lv.st.sbogdano.cinema.startup

import android.app.Application
import android.content.Context
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.internal.util.SingleLiveData
import lv.st.sbogdano.domain.entity.GenreType
import lv.st.sbogdano.domain.interactor.GenreTypeGetAllUseCase

class StartupViewModel(context: Context,
                       private val genreTypeGetAllUseCase: GenreTypeGetAllUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val _result = SingleLiveData<Boolean>()
    val result = _result
    private val _error = SingleLiveData<String>()
    val error = _error

    fun startup() = addDisposable(getAllGenreTypes())

    private fun getAllGenreTypes(): Disposable {
        return genreTypeGetAllUseCase.execute()
                .subscribeWith(object : DisposableObserver<List<GenreType>>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: List<GenreType>) {
                        result.value = true
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage ?: e.message ?: context.getString(R.string.unknown_error)
                    }
                })
    }
}