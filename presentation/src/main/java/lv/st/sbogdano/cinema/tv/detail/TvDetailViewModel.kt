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
import lv.st.sbogdano.cinema.tv.mapper.TvMapper
import lv.st.sbogdano.cinema.tv.model.Tv
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdUseCase
import lv.st.sbogdano.domain.interactor.TvGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase
import lv.st.sbogdano.domain.model.CreditDomainModel
import lv.st.sbogdano.domain.model.ReviewDomainModel
import lv.st.sbogdano.domain.model.TvDomainModel
import lv.st.sbogdano.domain.model.VideoDomainModel

class TvDetailViewModel(
    context: Context,
    private val tvGetByIdUseCase: TvGetByIdUseCase,
    private val creditsGetByIdUseCase: CreditsGetByIdUseCase,
    private val videosGetByIdUseCase: VideosGetByIdUseCase,
    private val reviewGetByIdUseCase: ReviewGetByIdUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = TvMapper()
    private val path = "tv"

    val loading = ObservableBoolean()
    val tv = ObservableField<Tv>()
    val credits = ObservableArrayList<CreditDomainModel>()
    val reviews = ObservableArrayList<ReviewDomainModel>()
    val video = ObservableField<VideoDomainModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadTvDetail(id: Int) = addDisposable(getTvById(id))
    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))
    fun loadVideos(id: Int) = addDisposable(getVideosById(id))
    fun loadReviews(id: Int) = addDisposable(getReviewsById(id))

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
}