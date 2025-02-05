package lv.st.sbogdano.cinema.tv

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.tv.list.TvListFragment

class TvPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val tvs = arrayOf(
            Pair("Popular", "popular"),
            Pair("Top Rated", "top_rated"),
            Pair("On Tv", "on_the_air"),
            Pair("Airing Today", "airing_today")
    )

    override fun getItem(postition: Int): Fragment = TvListFragment.newInstance(tvs[postition].second)

    override fun getCount() = tvs.size

    override fun getPageTitle(position: Int) = tvs[position].first.toUpperCase()
}