package lv.st.sbogdano.cinema.movie.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.view.ViewGroup
import androidx.view.doOnPreDraw
import dagger.android.support.DaggerAppCompatActivity
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityMovieBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject

class MovieActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityMovieBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_movie)
    }

    private val movieDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.movieDetailViewModel = movieDetailViewModel

        val movie = navigator.getMovie(this)
        movieDetailViewModel.loadMovieDetail(movie)

        movieDetailViewModel.movie.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }
}
