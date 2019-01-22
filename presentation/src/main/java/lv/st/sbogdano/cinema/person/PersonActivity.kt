package lv.st.sbogdano.cinema.person

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.ActivityPersonBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject

class PersonActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityPersonBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_person)
    }

    private val personDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(PersonDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar(binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.personDetailViewModel = personDetailViewModel

        val person = navigator.getPerson(this)
        personDetailViewModel.loadPersonDetail(person)

        personDetailViewModel.person.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
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
