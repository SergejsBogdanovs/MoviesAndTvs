package lv.st.sbogdano.cinema.adapters

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import lv.st.sbogdano.cinema.adapters.casts.CastsAdapter
import lv.st.sbogdano.cinema.adapters.movies.MovieListAdapter
import lv.st.sbogdano.cinema.adapters.reviews.ReviewsAdapter
import lv.st.sbogdano.cinema.adapters.tvs.TvListAdapter
import lv.st.sbogdano.cinema.internal.util.imageSize
import lv.st.sbogdano.cinema.internal.util.numberOfColumns
import lv.st.sbogdano.cinema.movie.list.model.MovieListModel
import lv.st.sbogdano.cinema.tv.list.model.TvListModel
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Review

object ViewListBindingAdapters {

    @JvmStatic
    @BindingAdapter("movieAdapter", "movieCallbacks", requireAll = false)
    fun setMovieAdapter(recyclerView: RecyclerView, items: List<MovieListModel>?, callbacks: MovieListAdapter.Callbacks) {
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
    fun setTvAdapter(recyclerView: RecyclerView, items: List<TvListModel>?, callbacks: TvListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = TvListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

    @JvmStatic
    @BindingAdapter("castAdapter", "castCallbacks", requireAll = false)
    fun setCastAdapter(recyclerView: RecyclerView, items: List<Credit>?, callbacks: CastsAdapter.Callbacks) {
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
    fun setReviewsAdapter(recyclerView: RecyclerView, items: List<Review>?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ReviewsAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }
    }
}