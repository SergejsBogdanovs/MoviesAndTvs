package lv.st.sbogdano.cinema.tv.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Tv
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.TvDomainModel

class TvDetailViewModel(
    context: Context,
    private val tvGetByIdUseCase: TvGetByIdObservableUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    creditsGetByIdUseCase: CreditsGetByIdObservableUseCase,
    videosGetByIdUseCase: VideosGetByIdObservableUseCase,
    reviewGetByIdUseCase: ReviewGetByIdObservableUseCase,
    getFavoriteByIdUseCase: GetFavoriteByIdObservableUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application,
        creditsGetByIdUseCase,
        videosGetByIdUseCase,
        reviewGetByIdUseCase,
        getFavoriteByIdUseCase
) {

    val tv = ObservableField<Tv>()

    fun loadTvDetail(id: Int) {
        addDisposable(checkIsFavorite(id))
        addDisposable(getTvById(id))
    }

    fun addItemToFavorites(path: String) {
        if (!isFavorite.get()) {
            addDisposable(addToFavorites(tv.get()!!, path))
        } else {
            isInserted.value = false
        }
    }

    private fun getTvById(id: Int): Disposable {
        return tvGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<TvDomainModel>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: TvDomainModel) {
                        loading.set(false)
                        tv.set(tvMapper.toModel(result))
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

    private fun addToFavorites(tv: Tv, path: String): Disposable {
        val favoriteDomainModel = tvMapper.toDomainModel(tv, path)

        return addToFavoritesUseCase.execute(favoriteDomainModel)
                .subscribeWith(object : DisposableCompletableObserver() {

                    override fun onComplete() {
                        isFavorite.set(true)
                        isInserted.value = true
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }
                })
    }
}