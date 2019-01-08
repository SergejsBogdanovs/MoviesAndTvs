package lv.st.sbogdano.cinema.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.adapters.favorites.FavoriteListAdapter
import lv.st.sbogdano.cinema.databinding.FragmentFavoriteListBinding
import lv.st.sbogdano.cinema.favorite.model.Favorite
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject

class FavoriteListFragment : DaggerFragment(), FavoriteListAdapter.Callbacks {

    companion object {

        private const val ARG_FAVORITE_TYPE = "type"

        fun newInstance(favoriteType: String): FavoriteListFragment {
            val fragment = FavoriteListFragment()
            val args = Bundle()
            args.putSerializable(ARG_FAVORITE_TYPE, favoriteType)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var binder: FragmentFavoriteListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(FavoriteListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_list, container, false)
        binder.viewModel = viewModel
        binder.favoriteCallback = this
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val favoriteType = arguments?.getString(FavoriteListFragment.ARG_FAVORITE_TYPE)!!
        viewModel.loadFavoriteList(favoriteType)
    }

    override fun onItemClick(view: View, item: Favorite) {
    }
}