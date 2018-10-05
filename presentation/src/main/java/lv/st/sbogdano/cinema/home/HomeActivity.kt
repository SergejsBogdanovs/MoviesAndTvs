package lv.st.sbogdano.cinema.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.MenuItem
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityHomeBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.movie.type.adapter.MovieTypePagerAdapter

class HomeActivity : DaggerAppCompatActivity() {

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binder.toolbar)

//        binder.bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_movies -> {
//                    setMovieTypeAdapter()
//                    true
//                }
//                R.id.nav_tv -> {
//                    setTvTypeAdapter()
//                    true
//                }
//                R.id.nav_favorite -> {
//                    setFavoriteAdapter()
//                    true
//                }
//                else -> false
//            }
//        }
    }

    //
    fun onNavigationClick(item: MenuItem) =
            when (item.itemId) {
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

    private fun setFavoriteAdapter() {
        Toast.makeText(this, "Hello Favorite", Toast.LENGTH_SHORT).show()
    }

    private fun setTvTypeAdapter() {
        Toast.makeText(this, "Hello Tv", Toast.LENGTH_SHORT).show()
    }

    private fun setMovieTypeAdapter() {
        val movieTypes = arrayOf(
                Pair("Popular", "popular"),
                Pair("Top Rated", "top_rated"),
                Pair("Upcoming", "upcoming"),
                Pair("Now Playing", "now_playing")
        )
        val fm = (view_pager.context as FragmentActivity).supportFragmentManager
        view_pager.adapter = MovieTypePagerAdapter(fm, movieTypes)
    }
}
