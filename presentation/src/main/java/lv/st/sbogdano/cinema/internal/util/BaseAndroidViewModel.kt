package lv.st.sbogdano.cinema.internal.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.movie.mapper.MovieMapper
import lv.st.sbogdano.cinema.tv.mapper.TvMapper
import lv.st.sbogdano.domain.interactor.CreditsGetByIdObservableUseCase
import lv.st.sbogdano.domain.interactor.GetFavoriteByIdObservableUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdObservableUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdObservableUseCase
import lv.st.sbogdano.domain.model.CreditDomainModel
import lv.st.sbogdano.domain.model.FavoriteDomainModel
import lv.st.sbogdano.domain.model.ReviewDomainModel
import lv.st.sbogdano.domain.model.VideoDomainModel

@SuppressLint("StaticFieldLeak")
abstract class BaseAndroidViewModel(
    application: Application,
    private val creditsGetByIdUseCase: CreditsGetByIdObservableUseCase?,
    private val videosGetByIdUseCase: VideosGetByIdObservableUseCase?,
    private val reviewGetByIdUseCase: ReviewGetByIdObservableUseCase?,
    private val getFavoriteByIdUseCase: GetFavoriteByIdObservableUseCase?
) : AndroidViewModel(application) {

    constructor(application: Application) : this(
            application,
            null,
            null,
            null,
            null
    )

    protected val context: Context = application

    var isFavorite = ObservableBoolean()
    private val _isInserted = SingleLiveData<Boolean>()
    val isInserted = _isInserted

    private val compositeDisposable = CompositeDisposable()

    protected val movieMapper = MovieMapper()
    protected val tvMapper = TvMapper()

    val loading = ObservableBoolean()
    val credits = ObservableArrayList<CreditDomainModel>()
    val reviews = ObservableArrayList<ReviewDomainModel>()
    val video = ObservableField<VideoDomainModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadCredits(id: Int, path: String) = addDisposable(getCreditsById(id, path))
    fun loadVideos(id: Int, path: String) = addDisposable(getVideosById(id, path))
    fun loadReviews(id: Int, path: String) = addDisposable(getReviewsById(id, path))

    private fun getCreditsById(id: Int, path: String): Disposable {
        val params = Pair(id, path)
        return creditsGetByIdUseCase!!.execute(params)
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

    private fun getVideosById(id: Int, path: String): Disposable {
        val params = Pair(id, path)
        return videosGetByIdUseCase!!.execute(params)
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

    private fun getReviewsById(id: Int, path: String): Disposable {
        val params = Pair(id, path)
        return reviewGetByIdUseCase!!.execute(params)
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

    protected fun checkIsFavorite(movieId: Int): Disposable {
        return getFavoriteByIdUseCase!!.execute(movieId)
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

    fun addDisposable(disposable: Disposable) {
        compositeDisposable += disposable
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}

private operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}
