package lv.st.sbogdano.cinema.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import lv.st.sbogdano.cinema.adapters.casts.CastsAdapter
import lv.st.sbogdano.cinema.adapters.favorites.FavoriteListAdapter
import lv.st.sbogdano.cinema.adapters.movies.MovieListAdapter
import lv.st.sbogdano.cinema.adapters.reviews.ReviewsAdapter
import lv.st.sbogdano.cinema.adapters.tvs.TvListAdapter
import lv.st.sbogdano.cinema.favorite.model.Favorite
import lv.st.sbogdano.cinema.internal.util.imageSize
import lv.st.sbogdano.cinema.internal.util.numberOfColumns
import lv.st.sbogdano.cinema.movie.model.Movie
import lv.st.sbogdano.cinema.tv.model.Tv
import lv.st.sbogdano.domain.model.CreditDomainModel
import lv.st.sbogdano.domain.model.ReviewDomainModel

object ViewListBindingAdapters {

    @JvmStatic
    @BindingAdapter("movieAdapter", "movieCallbacks", requireAll = false)
    fun setMovieAdapter(recyclerView: RecyclerView, items: List<Movie>?, callbacks: MovieListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = MovieListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("tvAdapter", "tvCallbacks", requireAll = false)
    fun setTvAdapter(recyclerView: RecyclerView, items: List<Tv>?, callbacks: TvListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = TvListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("favoriteAdapter", "favoriteCallbacks", requireAll = false)
    fun setFavoriteAdapter(recyclerView: RecyclerView, items: List<Favorite>?, callbacks: FavoriteListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = FavoriteListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("castAdapter", "castCallbacks", requireAll = false)
    fun setCastAdapter(recyclerView: RecyclerView, items: List<CreditDomainModel>?, callbacks: CastsAdapter.Callbacks) {
        items?.let {
            val flexboxLayoutManager = FlexboxLayoutManager(recyclerView.context)
            flexboxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = CastsAdapter(it, callbacks)
            recyclerView.layoutManager = flexboxLayoutManager
        }
    }

    @JvmStatic
    @BindingAdapter("reviewsAdapter")
    fun setReviewsAdapter(recyclerView: RecyclerView, items: List<ReviewDomainModel>?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ReviewsAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }
    }
}