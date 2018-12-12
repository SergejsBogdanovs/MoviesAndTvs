package lv.st.sbogdano.cinema.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.favorite.list.FavoriteListFragment

class FavoritePagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val favorites = arrayOf(
            Pair("Movies", "movies"),
            Pair("Tvs", "tvs")
    )

    override fun getItem(position: Int): Fragment = FavoriteListFragment.newInstance(favorites[position].second)

    override fun getCount(): Int = favorites.size

    override fun getPageTitle(position: Int): CharSequence? = favorites[position].first.toUpperCase()
}