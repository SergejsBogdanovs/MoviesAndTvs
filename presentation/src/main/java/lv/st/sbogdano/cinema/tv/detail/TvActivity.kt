package lv.st.sbogdano.cinema.tv.detail

import android.os.Bundle
import android.view.MenuItem
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
import lv.st.sbogdano.cinema.databinding.ActivityTvBinding
import lv.st.sbogdano.cinema.internal.util.databinding.ViewBindingAdapters
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import lv.st.sbogdano.domain.model.CreditDomainModel
import javax.inject.Inject

const val PATH = "tv"

class TvActivity : DaggerAppCompatActivity(), CastsAdapter.Callbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityTvBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_tv)
    }

    private val tvDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(TvDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.tvDetailViewModel = tvDetailViewModel
        binder.castCallbacks = this

        lifecycle.addObserver(trailer_view)

        val id = navigator.getTv(this)
        tvDetailViewModel.loadTvDetail(id)
        tvDetailViewModel.loadCredits(id, PATH)
        tvDetailViewModel.loadVideos(id, PATH)
        tvDetailViewModel.loadReviews(id, PATH)

        binder.fabFavorite.setOnClickListener { tvDetailViewModel.addItemToFavorites(id, PATH) }

        tvDetailViewModel.tv.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })

        tvDetailViewModel.isInserted.observe(this, Observer {
            val text = if (it) getString(R.string.added_to_favorites) else getString(R.string.already_exist)
            ViewBindingAdapters.showShortMessage(window.decorView, text)
        })
    }

    override fun onItemClick(view: View, item: CreditDomainModel) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
