package lv.st.sbogdano.cinema.movie.type

import android.support.v4.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.movie.list.MovieListFragment

class MovieTypePagerAdapter(
    fm: android.support.v4.app.FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val movies = arrayOf(
            Pair("Popular", "popular"),
            Pair("Top Rated", "top_rated"),
            Pair("Upcoming", "upcoming"),
            Pair("Now Playing", "now_playing")
    )

    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return MovieListFragment.newInstance(movies[position].second)
    }

    override fun getCount() = movies.size

    override fun getPageTitle(position: Int) = movies[position].first.toUpperCase()
}
