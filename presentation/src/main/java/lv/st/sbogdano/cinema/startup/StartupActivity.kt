package lv.st.sbogdano.cinema.startup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import lv.st.sbogdano.cinema.internal.util.PreferencesHelper
import lv.st.sbogdano.cinema.internal.util.databinding.ViewBindingAdapters
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject


class StartupActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(StartupViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.result.observe(this, Observer {
            navigator.navigateToHome(this@StartupActivity)
        })

        viewModel.error.observe(this, Observer { error ->
            ViewBindingAdapters.showLongMessage(window.decorView, error,
                    object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            this@StartupActivity.finish()
                        }
                    })
        })

        viewModel.startup(preferencesHelper.movieType)
    }
}
