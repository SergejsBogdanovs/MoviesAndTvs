package lv.st.sbogdano.cinema.internal.injection.module.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.internal.injection.scope.HomeScope
import lv.st.sbogdano.cinema.movie.list.MovieListFragment
import lv.st.sbogdano.cinema.movie.list.MovieListViewModel
import lv.st.sbogdano.cinema.tv.list.TvListFragment
import lv.st.sbogdano.cinema.tv.list.TvListViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.FavoritesByTypeGetAllUseCase
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase
import lv.st.sbogdano.domain.interactor.TvsByTypeGetAllUseCase

@Module
internal abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTvListFragment(): TvListFragment

//    @ContributesAndroidInjector
//    internal abstract fun contributeFavoriteListFragment(): FavoriteListFragment

    @Module
    companion object {

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideMoviesByTypeGetAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): MoviesByTypeGetAllUseCase {
            return MoviesByTypeGetAllUseCase(schedulers, gateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideTvsByTypeGetAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): TvsByTypeGetAllUseCase {
            return TvsByTypeGetAllUseCase(schedulers, gateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideFavoritesByTypeGetAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): FavoritesByTypeGetAllUseCase {
            return FavoritesByTypeGetAllUseCase(schedulers, gateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
                context: Context,
                moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase,
                tvsByTypeGetAllUseCase: TvsByTypeGetAllUseCase
//                favoritesByTypeGetAllUseCase: FavoritesByTypeGetAllUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(MovieListViewModel::class.java) ->
                            MovieListViewModel(context, moviesByTypeGetAllUseCase) as T

                        modelClass.isAssignableFrom(TvListViewModel::class.java) ->
                            TvListViewModel(context, tvsByTypeGetAllUseCase) as T

//                        modelClass.isAssignableFrom(FavoriteListViewModel::class.java) ->
//                            FavoriteListViewModel(context, favoritesByTypeGetAllUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}