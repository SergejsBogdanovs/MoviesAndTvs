package lv.st.sbogdano.cinema.tv.detail

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
import lv.st.sbogdano.cinema.adapters.casts.CastsAdapter
import lv.st.sbogdano.cinema.databinding.ActivityTvBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import lv.st.sbogdano.domain.entity.Credit
import javax.inject.Inject

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

        val tv = navigator.getTv(this)
        tvDetailViewModel.loadTvDetail(tv)
        tvDetailViewModel.loadCredits(tv)
        tvDetailViewModel.loadVideos(tv)
        tvDetailViewModel.loadReviews(tv)

        tvDetailViewModel.tv.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
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
