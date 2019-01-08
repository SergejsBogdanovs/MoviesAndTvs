package lv.st.sbogdano.cinema.tv.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Tv
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.TvDomainModel

class TvDetailViewModel(
    context: Context,
    private val tvGetByIdUseCase: TvGetByIdUseCase,
    creditsGetByIdUseCase: CreditsGetByIdUseCase,
    videosGetByIdUseCase: VideosGetByIdUseCase,
    reviewGetByIdUseCase: ReviewGetByIdUseCase,
    addToFavoritesUseCase: AddToFavoritesUseCase,
    getFavoriteByIdUseCase: GetFavoriteByIdUseCase
) : BaseAndroidViewModel(
        context.applicationContext as Application,
        creditsGetByIdUseCase,
        videosGetByIdUseCase,
        reviewGetByIdUseCase,
        addToFavoritesUseCase,
        getFavoriteByIdUseCase
) {

    val tv = ObservableField<Tv>()

    fun loadTvDetail(id: Int) {
        addDisposable(checkIsFavorite(id))
        addDisposable(getTvById(id))
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
}