package lv.st.sbogdano.cinema.startup

import android.app.Application
import android.content.Context
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.internal.util.SingleLiveData
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase
import lv.st.sbogdano.domain.model.MovieDomainModel

class StartupViewModel(
    context: Context,
    private val moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val _result = SingleLiveData<Boolean>()
    val result = _result
    private val _error = SingleLiveData<String>()
    val singleError = _error

    fun startup(type: String, refresh: Boolean = true) = addDisposable(getMoviesByType(type, refresh))

    private fun getMoviesByType(type: String, refresh: Boolean): Disposable {
        val params = Pair(type, refresh)
        return moviesByTypeGetAllUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<MovieDomainModel>>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: List<MovieDomainModel>) {
                        result.value = true
                    }

                    override fun onError(e: Throwable) {
                        singleError.value = e.localizedMessage ?: e.message ?: context.getString(R.string.unknown_error)
                    }
                })
    }
}