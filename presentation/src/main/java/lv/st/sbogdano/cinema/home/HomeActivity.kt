package lv.st.sbogdano.cinema.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityHomeBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.movie.type.MovieTypePagerAdapter
import lv.st.sbogdano.cinema.tv.type.TvTypePagerAdapter

class HomeActivity : DaggerAppCompatActivity() {

    companion object {

        @JvmField
        val TV = arrayOf(
                Pair("Popular", "popular"),
                Pair("Top Rated", "top_rated"),
                Pair("On Tv", "on_the_air"),
                Pair("Airing Today", "airing_today")
        )

        @JvmField
        val MOVIES = arrayOf(
                Pair("Popular", "popular"),
                Pair("Top Rated", "top_rated"),
                Pair("Upcoming", "upcoming"),
                Pair("Now Playing", "now_playing")
        )
    }

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val fm by lazy {
        (view_pager.context as FragmentActivity).supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binder.toolbar)

        setMovieTypeAdapter()

        binder.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movies -> {
                    setMovieTypeAdapter()
                    true
                }
                R.id.nav_tv -> {
                    setTvTypeAdapter()
                    true
                }
                R.id.nav_favorite -> {
                    setFavoriteAdapter()
                    true
                }
                else -> false
            }
        }
    }

    private fun setFavoriteAdapter() {
        Toast.makeText(this, "Hello Favorite", Toast.LENGTH_SHORT).show()
    }

    private fun setTvTypeAdapter() {
        view_pager.adapter = TvTypePagerAdapter(fm, TV)
    }

    private fun setMovieTypeAdapter() {
        view_pager.adapter = MovieTypePagerAdapter(fm, MOVIES)
    }
}
