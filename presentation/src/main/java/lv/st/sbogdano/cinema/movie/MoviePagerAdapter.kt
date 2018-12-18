package lv.st.sbogdano.cinema.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import lv.st.sbogdano.cinema.movie.list.MovieListFragment

class MoviePagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm) {

    private val movies = arrayOf(
            Pair("Popular", "popular"),
            Pair("Top Rated", "top_rated"),
            Pair("Upcoming", "upcoming"),
            Pair("Now Playing", "now_playing")
    )

    override fun getItem(position: Int): Fragment = MovieListFragment.newInstance(movies[position].second)

    override fun getCount() = movies.size

    override fun getPageTitle(position: Int) = movies[position].first.toUpperCase()
}
