package lv.st.sbogdano.cinema.movie.type

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.movie.list.MovieListFragment

class MovieTypePagerAdapter(
    fm: FragmentManager,
    private val items: Array<Pair<String, String>>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MovieListFragment.newInstance(items[position].second)
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position].first
}
