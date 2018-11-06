package lv.st.sbogdano.cinema.tv.type

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.tv.list.TvListFragment

class TvTypePagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val tvs = arrayOf(
            Pair("Popular", "popular"),
            Pair("Top Rated", "top_rated"),
            Pair("On Tv", "on_the_air"),
            Pair("Airing Today", "airing_today")
    )

    override fun getItem(postition: Int): Fragment {
        return TvListFragment.newInstance(tvs[postition].second)
    }

    override fun getCount() = tvs.size

    override fun getPageTitle(position: Int) = tvs[position].first.toUpperCase()
}