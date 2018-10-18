package lv.st.sbogdano.cinema.tv.type

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.tv.list.TvListFragment

class TvTypePagerAdapter(
    fm: FragmentManager,
    private val items: Array<Pair<String, String>>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(postition: Int): Fragment {
        return TvListFragment.newInstance(items[postition].second)
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position].first.toUpperCase()
}