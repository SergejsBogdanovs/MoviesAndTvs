package lv.st.sbogdano.cinema.movie.list.adapter

import android.support.v7.widget.RecyclerView
import androidx.databinding.BindingAdapter
import lv.st.sbogdano.cinema.movie.list.model.MovieModel
import android.support.v7.widget.GridLayoutManager
import android.util.DisplayMetrics


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("movieAdapter", "movieCallbacks", requireAll = false)
    fun setMovieAdapter(recyclerView: RecyclerView, items: List<MovieModel>?, callbacks: MovieListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = MovieListAdapter(it, callbacks)
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }

    private fun numberOfColumns(): Int {
        val displayMetrics = DisplayMetrics()
        // You can change this divider to adjust the size of the poster
        val widthDivider = 500
        val width = displayMetrics.widthPixels
        val nColumns = width / widthDivider
        return if (nColumns < 2) 2 else nColumns //to keep the grid aspect
    }
}