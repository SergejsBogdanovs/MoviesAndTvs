package lv.st.sbogdano.cinema.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentPersonBiographyBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import javax.inject.Inject

class PersonBiographyFragment : DaggerFragment() {

    companion object {
        fun newInstance(): PersonBiographyFragment = PersonBiographyFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binder: FragmentPersonBiographyBinding

    private val viewModel by lazyThreadSafetyNone {
        activity?.let { ViewModelProviders.of(it, viewModelFactory).get(PersonDetailViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_person_biography, container, false)
        binder.viewModel = viewModel
        return binder.root
    }
}
