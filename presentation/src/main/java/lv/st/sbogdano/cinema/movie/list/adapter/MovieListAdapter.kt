package lv.st.sbogdano.cinema.movie.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentMovieListBinding
import lv.st.sbogdano.cinema.databinding.FragmentMovieListItemBinding
import lv.st.sbogdano.cinema.movie.list.model.MovieModel

class MovieListAdapter(private val items: List<MovieModel>,
                       private val callbacks: Callbacks? = null)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){

    interface Callbacks {
        fun onItemClick(view: View, item: MovieModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentMovieListItemBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_list_item,
                parent,
                false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {

    }

    inner class ViewHolder(val binding: FragmentMovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}