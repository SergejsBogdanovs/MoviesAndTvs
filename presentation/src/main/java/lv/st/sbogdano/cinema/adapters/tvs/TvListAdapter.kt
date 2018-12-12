package lv.st.sbogdano.cinema.adapters.tvs

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentTvListItemBinding
import lv.st.sbogdano.cinema.tv.list.model.TvListModel

class TvListAdapter(
        private val items: List<TvListModel>,
        private val callbacks: Callbacks? = null,
        private val imageSize: Pair<Int, Int>
) : RecyclerView.Adapter<TvListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: TvListModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: FragmentTvListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_list_item, parent, false)
        setImageViewLayoutParams(binding)
        return ViewHolder(binding)
    }

    private fun setImageViewLayoutParams(binding: FragmentTvListItemBinding) {
        val imageView = binding.imageTvPoster
        val layoutParams: ViewGroup.LayoutParams = imageView.layoutParams
        val (width, height) = imageSize
        layoutParams.width = width
        layoutParams.height = height
        imageView.requestLayout()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tv = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: FragmentTvListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}