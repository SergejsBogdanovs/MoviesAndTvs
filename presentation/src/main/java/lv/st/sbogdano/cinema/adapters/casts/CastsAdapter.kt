package lv.st.sbogdano.cinema.adapters.casts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.CastItemBinding
import lv.st.sbogdano.domain.model.CreditDomainModel

class CastsAdapter(
    private val items: List<CreditDomainModel>,
    private val callbacks: Callbacks? = null
) : RecyclerView.Adapter<CastsAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: CreditDomainModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CastItemBinding = DataBindingUtil.inflate(inflater, R.layout.cast_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CastsAdapter.ViewHolder, position: Int) {
        holder.binding.creditDomainModel = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}