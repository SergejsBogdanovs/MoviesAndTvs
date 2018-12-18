package lv.st.sbogdano.cinema.internal.util.databinding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

object ViewPagerBindingAdapters {

    @JvmStatic
    @BindingAdapter("setupWithViewPager")
    fun setupWithViewPager(tabLayout: TabLayout, viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)
    }
}