package lv.st.sbogdano.cinema.adapters.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ReviewItemBinding
import lv.st.sbogdano.domain.model.ReviewDomainModel

class ReviewsAdapter(
    private val items: List<ReviewDomainModel>
) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ReviewItemBinding = DataBindingUtil.inflate(inflater, R.layout.review_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.binding.reviewDomainModel = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}