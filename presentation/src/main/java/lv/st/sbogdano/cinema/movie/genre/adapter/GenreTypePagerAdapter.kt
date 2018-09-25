package lv.st.sbogdano.cinema.movie.genre.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.movie.list.GenreListFragment
import lv.st.sbogdano.domain.entity.GenreType

class GenreTypePagerAdapter(fm: FragmentManager,
                            private val items: List<GenreType>)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return GenreListFragment.newInstance(items[position].id)
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position].name

}