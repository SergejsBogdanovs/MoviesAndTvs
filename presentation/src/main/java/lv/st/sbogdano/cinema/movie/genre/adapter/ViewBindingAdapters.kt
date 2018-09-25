package lv.st.sbogdano.cinema.movie.genre.adapter

import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import androidx.databinding.BindingAdapter
import lv.st.sbogdano.domain.entity.GenreType

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("genreTypeAdapter")
    fun setGenreTypeAdapter(viewPager: ViewPager, items: List<GenreType>?) {
        items?.let {
            val fm = (viewPager.context as FragmentActivity).supportFragmentManager
            viewPager.adapter = GenreTypePagerAdapter(fm, items)
        }
    }
}