package lv.st.sbogdano.cinema.movie.detail.adapter

import android.databinding.BindingAdapter
import com.github.lzyzsd.circleprogress.ArcProgress

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

}