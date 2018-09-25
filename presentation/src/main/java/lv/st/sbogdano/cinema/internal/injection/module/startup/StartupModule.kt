package lv.st.sbogdano.cinema.internal.injection.module.startup

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.StartupScope
import lv.st.sbogdano.cinema.startup.StartupViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.SystemGateway
import lv.st.sbogdano.domain.interactor.GenreTypeGetAllUseCase

@Module
internal class StartupModule {

    @StartupScope
    @Provides
    internal fun provideGenreTypeGetAllUseCase(schedulers: Schedulers,
                                               systemGateWay: SystemGateway): GenreTypeGetAllUseCase {
        return GenreTypeGetAllUseCase(schedulers, systemGateWay)
    }

    @Provides
    internal fun provideViewModelFactory(context: Context,
                                         genresGetAllUseCase: GenreTypeGetAllUseCase): ViewModelProvider.Factory {

        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return when {
                    modelClass.isAssignableFrom(StartupViewModel::class.java) ->
                        StartupViewModel(context, genresGetAllUseCase) as T

//                    modelClass.isAssignableFrom(GenreTypeViewModel::class.java) ->
//                        GenreTypeViewModel(context, genresGetAllUseCase) as T

                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            }
        }
    }
}