package lv.st.sbogdano.cinema.tv.detail

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.cinema.tv.detail.mapper.TvMapper
import lv.st.sbogdano.cinema.tv.detail.model.TvModel
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Review
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.entity.Video
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdUseCase
import lv.st.sbogdano.domain.interactor.TvGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase

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
    val tv = ObservableField<TvModel>()
    val credits = ObservableArrayList<Credit>()
    val reviews = ObservableArrayList<Review>()
    val video = ObservableField<Video>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadTvDetail(id: Int) = addDisposable(getTvById(id))
    fun loadCredits(id: Int) = addDisposable(getCreditsById(id))
    fun loadVideos(id: Int) = addDisposable(getVideosById(id))
    fun loadReviews(id: Int) = addDisposable(getReviewsById(id))

    private fun getTvById(id: Int): Disposable {
        return tvGetByIdUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<Tv>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: Tv) {
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
                .subscribeWith(object : DisposableObserver<List<Credit>>() {
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<Credit>) {
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
                .subscribeWith(object : DisposableObserver<List<Video>>() {
                    override fun onNext(result: List<Video>) {
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
                .subscribeWith(object : DisposableObserver<List<Review>>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: List<Review>) {
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