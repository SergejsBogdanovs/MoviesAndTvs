package lv.st.sbogdano.cinema.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentActivity
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
        setMovieTypeAdapter()
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
