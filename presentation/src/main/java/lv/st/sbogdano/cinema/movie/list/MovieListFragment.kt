package lv.st.sbogdano.cinema.movie.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.android.support.DaggerFragment
import lv.st.sbogdano.cinema.R
import lv.st.sbogdano.cinema.databinding.FragmentMovieListBinding
import lv.st.sbogdano.cinema.internal.util.lazyThreadSafetyNone
import lv.st.sbogdano.cinema.movie.list.adapter.MovieListAdapter
import lv.st.sbogdano.cinema.movie.list.model.MovieModel
import lv.st.sbogdano.cinema.navigation.Navigator
import javax.inject.Inject

class MovieListFragment : DaggerFragment(), MovieListAdapter.Callbacks{

    companion object {

        private const val ARG_TYPE = "type"

        fun newInstance(movieType: String): MovieListFragment {
            val fragment = MovieListFragment()
            val args = Bundle()
            args.putSerializable(ARG_TYPE, movieType)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var binder: FragmentMovieListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binder.viewModel = viewModel
        binder.movieCallbacks = this
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movieType = arguments?.getString(ARG_TYPE)!!
        viewModel.loadMovieList(movieType)
    }

    override fun onItemClick(view: View, item: MovieModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}