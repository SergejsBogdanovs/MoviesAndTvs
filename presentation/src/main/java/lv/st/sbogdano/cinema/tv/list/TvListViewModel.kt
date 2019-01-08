package lv.st.sbogdano.cinema.tv.list

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Tv
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.tv.mapper.TvMapper
import lv.st.sbogdano.domain.interactor.TvsByTypeGetAllUseCase
import lv.st.sbogdano.domain.model.TvDomainModel

class TvListViewModel(
    context: Context,
    private val tvsByTypeGetAllUseCase: TvsByTypeGetAllUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = TvMapper()

    val result = ObservableArrayList<Tv>()

    private var tvType = ""

    fun loadTvList(type: String, refresh: Boolean = false) {
        this.tvType = type
        addDisposable(findTvByType(type, refresh))
    }

    fun refresh() = loadTvList(tvType, true)

    private fun findTvByType(type: String, refresh: Boolean): Disposable {
        val params = Pair(type, refresh)
        return tvsByTypeGetAllUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<TvDomainModel>>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<TvDomainModel>) {
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