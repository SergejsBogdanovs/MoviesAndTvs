package lv.st.sbogdano.cinema.internal.injection.module.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.internal.injection.scope.HomeScope
import lv.st.sbogdano.cinema.movie.list.MovieListFragment
import lv.st.sbogdano.cinema.movie.list.MovieListViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase

@Module
internal abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment(): MovieListFragment

    @Module
    companion object {

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideMoviesByTypeGetAllUseCase(schedulers: Schedulers,
                                                      gateway: Gateway): MoviesByTypeGetAllUseCase {
            return MoviesByTypeGetAllUseCase(schedulers, gateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(MovieListViewModel::class.java) ->
                            MovieListViewModel(context, moviesByTypeGetAllUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}