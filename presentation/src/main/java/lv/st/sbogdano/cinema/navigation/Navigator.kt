package lv.st.sbogdano.cinema.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import lv.st.sbogdano.cinema.home.HomeActivity
import lv.st.sbogdano.cinema.movie.detail.MovieActivity

class Navigator {

    companion object {
        private val EXTRA_MOVIE = "${MovieActivity::class.java.`package`.name}.extra.MOVIE"
    }

    fun navigateToHome(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }

    fun navigateToMovie(activity: Activity, movie: Int, sharedView: Pair<View, String>) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movie)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun getMovie(activity: Activity) = activity.intent.getIntExtra(EXTRA_MOVIE, 0)

}