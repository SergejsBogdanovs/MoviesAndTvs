package lv.st.sbogdano.cinema.tv.list.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import lv.st.sbogdano.cinema.internal.util.imageSize
import lv.st.sbogdano.cinema.internal.util.numberOfColumns
import lv.st.sbogdano.cinema.tv.list.model.TvModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("tvAdapter", "tvCallbacks", requireAll = false)
    fun setTvAdapter(recyclerView: RecyclerView, items: List<TvModel>?, callbacks: TvListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = TvListAdapter(it, callbacks, imageSize(context))
                layoutManager = GridLayoutManager(context, numberOfColumns())
            }
        }
    }
}