package lv.st.sbogdano.cinema.tv.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.adapters.tvs.TvListAdapter
import lv.st.sbogdano.cinema.basemodel.Tv
import lv.st.sbogdano.cinema.databinding.FragmentTvListBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject

class TvListFragment : DaggerFragment(), TvListAdapter.Callbacks {

    companion object {

        private const val ARG_TV_TYPE = "type"

        fun newInstance(tvType: String): TvListFragment {
            val fragment = TvListFragment()
            val args = Bundle()
            args.putSerializable(ARG_TV_TYPE, tvType)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var binder: FragmentTvListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(TvListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_list, container, false)
        binder.viewModel = viewModel
        binder.tvCallbacks = this
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tvType = arguments?.getString(TvListFragment.ARG_TV_TYPE)!!
        viewModel.loadTvList(tvType)
    }

    override fun onItemClick(view: View, item: Tv) {
        val imageView = view.findViewById<View>(R.id.image_tv_poster)
        val sharedView = Pair(imageView, ViewCompat.getTransitionName(imageView))
        activity?.let { navigator.navigateToTv(it, item.id, sharedView) }
    }
}