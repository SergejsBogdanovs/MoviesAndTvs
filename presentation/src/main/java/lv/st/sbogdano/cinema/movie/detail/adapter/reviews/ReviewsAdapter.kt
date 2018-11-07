package lv.st.sbogdano.cinema.movie.detail.adapter.reviews

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ReviewItemBinding
import lv.st.sbogdano.domain.entity.Review

class ReviewsAdapter(
    private val items: List<Review>
) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ReviewItemBinding = DataBindingUtil.inflate(inflater, R.layout.review_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ReviewsAdapter.ViewHolder, position: Int) {
        holder.binding.review = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}