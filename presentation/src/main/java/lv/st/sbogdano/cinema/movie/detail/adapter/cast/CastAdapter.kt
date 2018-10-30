package lv.st.sbogdano.cinema.movie.detail.adapter.cast

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.CastItemBinding
import lv.st.sbogdano.domain.entity.Credit

class CastAdapter(
    private val items: List<Credit>,
    private val callbacks: Callbacks? = null
) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: Credit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CastItemBinding = DataBindingUtil.inflate(inflater, R.layout.cast_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {
        holder.binding.credit = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}