package lv.st.sbogdano.cinema.movie.detail.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.lzyzsd.circleprogress.ArcProgress
import lv.st.sbogdano.cinema.movie.detail.adapter.cast.CastAdapter
import lv.st.sbogdano.domain.entity.Credit

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
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = CastAdapter(it, callbacks)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}