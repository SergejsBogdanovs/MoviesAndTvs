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

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val fm by lazy {
        (view_pager.context as FragmentActivity).supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binder.toolbar)

        view_pager.adapter = MovieTypePagerAdapter(fm)

        binder.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_movies -> {
                    setMovieTypeAdapter()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tv -> {
                    setTvTypeAdapter()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_favorite -> {
                    setFavoriteAdapter()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setMovieTypeAdapter() {
        if (binder.bottomNavigation.selectedItemId != R.id.nav_movies) {
            view_pager.adapter = MovieTypePagerAdapter(fm)
        }
    }

    private fun setTvTypeAdapter() {
        if (binder.bottomNavigation.selectedItemId != R.id.nav_tv) {
            view_pager.adapter = TvTypePagerAdapter(fm)
        }
    }

    private fun setFavoriteAdapter() {
        Toast.makeText(this, "Hello Favorite", Toast.LENGTH_SHORT).show()
    }
}
