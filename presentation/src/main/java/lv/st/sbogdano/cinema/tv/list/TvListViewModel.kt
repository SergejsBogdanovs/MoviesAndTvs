package lv.st.sbogdano.cinema.tv.list

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.tv.list.model.TvListModel
import lv.st.sbogdano.cinema.tv.list.mapper.TvMapper
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.interactor.TvsByTypeGetAllUseCase

class TvListViewModel(
    context: Context,
    private val tvsByTypeGetAllUseCase: TvsByTypeGetAllUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = TvMapper()

    val loading = ObservableBoolean()
    val result = ObservableArrayList<TvListModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    private var tvType = ""

    fun loadTvList(type: String, refresh: Boolean = false) {
        this.tvType = type
        addDisposable(findTvByType(type, refresh))
    }

    fun refresh() = loadTvList(tvType, true)

    private fun findTvByType(type: String, refresh: Boolean): Disposable {
        val params = Pair(type, refresh)
        return tvsByTypeGetAllUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<Tv>>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<Tv>) {
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