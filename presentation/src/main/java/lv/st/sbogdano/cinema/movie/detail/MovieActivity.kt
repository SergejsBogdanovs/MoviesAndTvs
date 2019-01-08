package lv.st.sbogdano.cinema.movie.detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movie.*
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.adapters.casts.CastsAdapter
import lv.st.sbogdano.cinema.databinding.ActivityMovieBinding
import lv.st.sbogdano.cinema.internal.util.databinding.ViewBindingAdapters
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import lv.st.sbogdano.domain.model.CreditDomainModel
import javax.inject.Inject

const val PATH = "movie"

class MovieActivity : DaggerAppCompatActivity(), CastsAdapter.Callbacks {

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
        movieDetailViewModel.loadMovieDetail(movie.id)
        movieDetailViewModel.loadCredits(movie.id, PATH)
        movieDetailViewModel.loadVideos(movie.id, PATH)
        movieDetailViewModel.loadReviews(movie.id, PATH)

        binder.fabFavorite.setOnClickListener { movieDetailViewModel.addItemToFavorites(movie, PATH) }

        movieDetailViewModel.movie.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })

        movieDetailViewModel.isInserted.observe(this, Observer {
            val text = if (it) getString(R.string.added_to_favorites) else getString(R.string.already_exist)
            ViewBindingAdapters.showShortMessage(window.decorView, text)
        })
    }

    override fun onItemClick(view: View, item: CreditDomainModel) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
    }
}
