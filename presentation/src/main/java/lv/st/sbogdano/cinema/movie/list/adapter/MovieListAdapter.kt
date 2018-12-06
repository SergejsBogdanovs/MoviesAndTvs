package lv.st.sbogdano.cinema.movie.list.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentMovieListItemBinding
import lv.st.sbogdano.cinema.movie.list.model.MovieListModel
import lv.st.sbogdano.domain.entity.Movie

class MovieListAdapter(
        private val items: List<MovieListModel>,
        private val callbacks: Callbacks? = null,
        private val imageSize: Pair<Int, Int>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: MovieListModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentMovieListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list_item, parent, false)
        setImageViewLayoutParams(binding)
        return ViewHolder(binding)
    }

    private fun setImageViewLayoutParams(binding: FragmentMovieListItemBinding) {
        val imageView = binding.imageMoviePoster
        val layoutParams: ViewGroup.LayoutParams = imageView.layoutParams
        val (width, height) = imageSize
        layoutParams.width = width
        layoutParams.height = height
        imageView.requestLayout()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.binding.movie = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: FragmentMovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}