package lv.st.sbogdano.cinema.internal.injection.module.person

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import lv.st.sbogdano.cinema.internal.injection.scope.PersonScope
import lv.st.sbogdano.cinema.person.PersonBiographyFragment
import lv.st.sbogdano.cinema.person.PersonDetailViewModel
import lv.st.sbogdano.cinema.person.knownfor.KnownForListFragment
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.MovieCreditsGetByPersonIdObservableUseCase
import lv.st.sbogdano.domain.interactor.PersonGetByIdObservableUseCase

@Module
internal abstract class PersonModule {

    @ContributesAndroidInjector
    internal abstract fun contributePersonBiographyFragment(): PersonBiographyFragment

    @ContributesAndroidInjector
    internal abstract fun contributeKnownForListFragment(): KnownForListFragment

    @Module
    companion object {

        @PersonScope
        @Provides
        @JvmStatic
        internal fun providePersonGetByIdUseCase(
                schedulers: Schedulers,
                gateway: Gateway
        ): PersonGetByIdObservableUseCase {
            return PersonGetByIdObservableUseCase(schedulers, gateway)
        }

        @PersonScope
        @Provides
        @JvmStatic
        internal fun provideMovieCreditsGetByPersonIdUseCase(
                schedulers: Schedulers,
                gateway: Gateway
        ): MovieCreditsGetByPersonIdObservableUseCase {
            return MovieCreditsGetByPersonIdObservableUseCase(schedulers, gateway)
        }

        @PersonScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
                context: Context,
                personGetByIdUseCase: PersonGetByIdObservableUseCase,
                movieCreditsGetByPersonIdUseCase: MovieCreditsGetByPersonIdObservableUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(PersonDetailViewModel::class.java) ->
                            PersonDetailViewModel(context, personGetByIdUseCase, movieCreditsGetByPersonIdUseCase) as T
                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }

    }
}