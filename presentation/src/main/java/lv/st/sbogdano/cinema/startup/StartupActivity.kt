package lv.st.sbogdano.cinema.startup

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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

        viewModel.singleError.observe(this, Observer { error ->
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
