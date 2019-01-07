package lv.st.sbogdano.cinema.tv.detail

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.internal.util.SingleLiveData
import lv.st.sbogdano.cinema.tv.mapper.TvMapper
import lv.st.sbogdano.cinema.tv.model.Tv
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.*

class TvDetailViewModel(
    context: Context,
    private val tvGetByIdUseCase: TvGetByIdUseCase,
    private val creditsGetByIdUseCase: CreditsGetByIdUseCase,
    private val videosGetByIdUseCase: VideosGetByIdUseCase,
    private val reviewGetByIdUseCase: ReviewGetByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = TvMapper()
    private val path = "tv"
    private val _isInserted = SingleLiveData<Boolean>()
    val isInserted = _isInserted

    val loading = ObservableBoolean()
    val tv = ObservableField<Tv>()
    val credits = ObservableArrayList<CreditDomainModel>()
    val reviews = ObservableArrayList<ReviewDomainModel>()
    val video = ObservableField<VideoDomainModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()
    var isFavorite = ObservableBoolean()

    fun loadTvDetail(id: Int) {
        addDisposable(checkIsFavorite(id))
        addDisposable(getTvById(id))
    }
    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))
    fun loadVideos(id: Int) = addDisposable(getVideosById(id))
    fun loadReviews(id: Int) = addDisposable(getReviewsById(id))
    fun addTvToFavorites(tv: Tv) {
        if (!isFavorite.get()) {
            addDisposable(addToFavorites(tv))
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
                        tv.set(mapper.toModel(result))
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
        val params = Pair(id, path)
        return creditsGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<CreditDomainModel>>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<CreditDomainModel>) {
                        loading.set(false)
                        credits.clear()
                        credits.addAll(result)
                        empty.set(result.isEmpty())
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
        val params = Pair(id, path)
        return videosGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<VideoDomainModel>>() {
                    override fun onNext(result: List<VideoDomainModel>) {
                        video.set(result.first())
                        empty.set(result.isEmpty())
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                    }
                })
    }

    private fun getReviewsById(id: Int): Disposable {
        val params = Pair(id, path)
        return reviewGetByIdUseCase.execute(params)
                .subscribeWith(object : DisposableObserver<List<ReviewDomainModel>>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<ReviewDomainModel>) {
                        loading.set(false)
                        reviews.clear()
                        reviews.addAll(result)
                        empty.set(result.isEmpty())
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

    private fun addToFavorites(tv: Tv): Disposable {
        val favoriteDomainModel = mapper.toDomainModel(tv, path)
        return addToFavoritesUseCase.execute(favoriteDomainModel)
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: Long) {
                        isFavorite.set(true)
                        isInserted.value = true
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }
                })
    }

    private fun checkIsFavorite(movieId: Int): Disposable {
        return getFavoriteByIdUseCase.execute(movieId)
                .subscribeWith(object : DisposableObserver<FavoriteDomainModel>() {
                    override fun onComplete() {}

                    override fun onNext(t: FavoriteDomainModel) {
                        isFavorite.set(true)
                    }

                    override fun onError(e: Throwable) {
                        error.set(e.localizedMessage ?: e.message
                        ?: context.getString(R.string.unknown_error))
                    }
                })
    }
}