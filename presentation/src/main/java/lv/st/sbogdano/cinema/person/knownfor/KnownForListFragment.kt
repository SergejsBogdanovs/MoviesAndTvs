package lv.st.sbogdano.cinema.person.knownfor


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentKnownForListBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.person.PersonDetailViewModel
import javax.inject.Inject

class KnownForListFragment : DaggerFragment() {

    companion object {

        private const val ARG_KNOWN_FOR = "known_for"

        fun newInstance(person: Int): KnownForListFragment {
            val fragment = KnownForListFragment()
            val args = Bundle()
            args.putInt(ARG_KNOWN_FOR, person)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binder: FragmentKnownForListBinding

    private val viewModel by lazyThreadSafetyNone {
        activity?.let { ViewModelProviders.of(it, viewModelFactory).get(PersonDetailViewModel::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_known_for_list, container, false)
        binder.viewModel = viewModel
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val person = arguments?.getInt(ARG_KNOWN_FOR)!!
        viewModel?.loadMovieCredits(person)
    }


}
