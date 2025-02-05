package lv.st.sbogdano.cinema.internal.injection.module.startup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.StartupScope
import lv.st.sbogdano.cinema.startup.StartupViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllObservableUseCase

@Module
internal class StartupModule {

    @StartupScope
    @Provides
    internal fun provideMoviesByTypeGetAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MoviesByTypeGetAllObservableUseCase {
        return MoviesByTypeGetAllObservableUseCase(schedulers, gateway)
    }

    @Provides
    internal fun provideViewModelFactory(
        context: Context,
        moviesByTypeGetAllUseCase: MoviesByTypeGetAllObservableUseCase
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