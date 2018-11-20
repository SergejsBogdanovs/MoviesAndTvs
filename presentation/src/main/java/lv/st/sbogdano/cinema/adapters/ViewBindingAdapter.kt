package lv.st.sbogdano.cinema.adapters

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.lzyzsd.circleprogress.ArcProgress
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import lv.st.sbogdano.cinema.adapters.casts.CastAdapter
import lv.st.sbogdano.cinema.adapters.reviews.ReviewsAdapter
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Review

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("arc_progress")
    fun setArcProgress(view: ArcProgress, progress: Int) {
        view.progress = progress
    }

    @JvmStatic
    @BindingAdapter("arc_progress")
    fun setArcProgress(view: ArcProgress, progress: Float) {
        view.progress = (progress * 10).toInt()
    }

    @JvmStatic
    @BindingAdapter("castAdapter", "castCallbacks", requireAll = false)
    fun setCastAdapter(recyclerView: RecyclerView, items: List<Credit>?, callbacks: CastAdapter.Callbacks) {
        items?.let {
            val flexboxLayoutManager = FlexboxLayoutManager(recyclerView.context)
            flexboxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = CastAdapter(it, callbacks)
            recyclerView.layoutManager = flexboxLayoutManager
        }
    }

    @JvmStatic
    @BindingAdapter("reviewsAdapter")
    fun setReviewsAdapter(recyclerView: RecyclerView, items: List<Review>?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = ReviewsAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }
    }
}