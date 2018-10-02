package lv.st.sbogdano.cinema.movie.list.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import lv.st.sbogdano.cinema.internal.util.imageSize
import lv.st.sbogdano.cinema.internal.util.numberOfColumns
import lv.st.sbogdano.cinema.movie.list.model.MovieModel


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("movieAdapter", "movieCallbacks", requireAll = false)
    fun setMovieAdapter(recyclerView: RecyclerView, items: List<MovieModel>?, callbacks: MovieListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = MovieListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

}