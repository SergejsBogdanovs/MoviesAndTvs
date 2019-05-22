package lv.st.sbogdano.cinema.adapters.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.basemodel.Favorite
import lv.st.sbogdano.cinema.databinding.FragmentFavoriteListItemBinding

class FavoriteListAdapter(
    private val items: List<Favorite>,
    private val callbacks: Callbacks? = null,
    private val imageSize: Pair<Int, Int>
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: Favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentFavoriteListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_list_item, parent, false)
        setImageViewLayoutParams(binding)
        return ViewHolder(binding)
    }

    private fun setImageViewLayoutParams(binding: FragmentFavoriteListItemBinding) {
        val imageView = binding.poster
        val layoutParams: ViewGroup.LayoutParams = imageView.layoutParams
        val (width, height) = imageSize
        layoutParams.width = width
        layoutParams.height = height
        imageView.requestLayout()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.favorite = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: FragmentFavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}