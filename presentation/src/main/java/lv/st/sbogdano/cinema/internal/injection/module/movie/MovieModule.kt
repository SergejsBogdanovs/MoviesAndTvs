package lv.st.sbogdano.cinema.internal.injection.module.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.MovieScope
import lv.st.sbogdano.cinema.movie.detail.MovieDetailViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase

@Module
internal abstract class MovieModule {

    @Module
    companion object {

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideMovieGetByIdUseCase(schedulers: Schedulers,
                                                gateway: Gateway): MovieGetByIdUseCase {
            return MovieGetByIdUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideCreditsGetByIdUseCase(schedulers: Schedulers,
                                                  gateway: Gateway): CreditsGetByIdUseCase {
            return CreditsGetByIdUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             movieGetByIdUseCase: MovieGetByIdUseCase,
                                             creditsGetByIdUseCase: CreditsGetByIdUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(MovieDetailViewModel::class.java) ->
                            MovieDetailViewModel(context, movieGetByIdUseCase, creditsGetByIdUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}