package lv.st.sbogdano.cinema.internal.injection.module.startup

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.StartupScope
import lv.st.sbogdano.cinema.startup.StartupViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase

@Module
internal class StartupModule {

    @StartupScope
    @Provides
    internal fun provideMoviesByTypeGetAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MoviesByTypeGetAllUseCase {
        return MoviesByTypeGetAllUseCase(schedulers, gateway)
    }

    @Provides
    internal fun provideViewModelFactory(
        context: Context,
        moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase
    ): ViewModelProvider.Factory {

        return object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return when {
                    modelClass.isAssignableFrom(StartupViewModel::class.java) ->
                        StartupViewModel(context, moviesByTypeGetAllUseCase) as T

                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}