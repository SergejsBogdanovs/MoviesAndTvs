package lv.st.sbogdano.cinema.movie.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.view.doOnPreDraw
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movie.*
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityMovieBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.movie.detail.adapter.casts.CastAdapter
import lv.st.sbogdano.cinema.navigation.Navigator
import lv.st.sbogdano.domain.entity.Credit
import javax.inject.Inject

class MovieActivity : DaggerAppCompatActivity(), CastAdapter.Callbacks {

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
        binder.castCallbacks = this

        lifecycle.addObserver(trailer_view)

        val movie = navigator.getMovie(this)
        movieDetailViewModel.loadMovieDetail(movie)
        movieDetailViewModel.loadCredits(movie)
        movieDetailViewModel.loadVideos(movie)
        movieDetailViewModel.loadReviews(movie)

        movieDetailViewModel.movie.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }

    override fun onItemClick(view: View, item: Credit) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
    }
}
