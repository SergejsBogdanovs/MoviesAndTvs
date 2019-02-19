package lv.st.sbogdano.cinema.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityHomeBinding
import lv.st.sbogdano.cinema.favorite.FavoritePagerAdapter
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.movie.MoviePagerAdapter
import lv.st.sbogdano.cinema.tv.TvPagerAdapter

const val BOTTOM_NAV_ITEM_KEY = "bottom_navigation_item_key"

class HomeActivity : DaggerAppCompatActivity() {

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val fm by lazy {
        (view_pager.context as FragmentActivity).supportFragmentManager
    }

    private var bottomNavigationItem: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationItem = savedInstanceState?.getInt(BOTTOM_NAV_ITEM_KEY) ?: R.id.nav_movies

        setSupportActionBar(binder.toolbar)

        setBottomNavigationItem(bottomNavigationItem)

        binder.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movies -> {
                    if (binder.bottomNavigation.selectedItemId != R.id.nav_movies) {
                        setMovieTypeAdapter()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tv -> {
                    if (binder.bottomNavigation.selectedItemId != R.id.nav_tv) {
                        setTvTypeAdapter()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_favorite -> {
                    if (binder.bottomNavigation.selectedItemId != R.id.nav_favorite) {
                        setFavoriteAdapter()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(BOTTOM_NAV_ITEM_KEY, binder.bottomNavigation.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavigationItem = savedInstanceState?.getInt(BOTTOM_NAV_ITEM_KEY)
    }

    private fun setBottomNavigationItem(item: Int?) {
        when (item) {
            R.id.nav_movies -> setMovieTypeAdapter()
            R.id.nav_tv -> setTvTypeAdapter()
            R.id.nav_favorite -> setFavoriteAdapter()
        }
    }

    private fun setMovieTypeAdapter() {
        view_pager.adapter = MoviePagerAdapter(fm)
    }

    private fun setTvTypeAdapter() {
        view_pager.adapter = TvPagerAdapter(fm)
    }

    private fun setFavoriteAdapter() {
        view_pager.adapter = FavoritePagerAdapter(fm)
    }
}
