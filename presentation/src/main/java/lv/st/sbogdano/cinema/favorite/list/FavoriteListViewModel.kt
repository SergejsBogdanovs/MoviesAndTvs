package lv.st.sbogdano.cinema.favorite.list

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableArrayList
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Favorite
import lv.st.sbogdano.cinema.favorite.mapper.FavoriteMapper
import lv.st.sbogdano.cinema.internal.util.BaseAndroidViewModel
import lv.st.sbogdano.domain.interactor.FavoritesByTypeGetAllObservableUseCase
import lv.st.sbogdano.domain.model.FavoriteDomainModel

class FavoriteListViewModel(
    context: Context,
    private val favoritesByTypeGetAllUseCase: FavoritesByTypeGetAllObservableUseCase
) : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = FavoriteMapper()

    val result = ObservableArrayList<Favorite>()

    private var favoriteType = ""

    fun loadFavoriteList(favoriteType: String) {
        this.favoriteType = favoriteType
        addDisposable(getFavorites(favoriteType))
    }

    fun refresh() = loadFavoriteList(favoriteType)

    private fun getFavorites(favoriteType: String): Disposable {
        return favoritesByTypeGetAllUseCase.execute(favoriteType)
                .subscribeWith(object : DisposableObserver<List<FavoriteDomainModel>>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<FavoriteDomainModel>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t.map { mapper.toModel(it) })
                        empty.set(t.isEmpty())
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