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
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.PersonGetByIdUseCase

@Module
internal abstract class PersonModule {

    @ContributesAndroidInjector
    internal abstract fun contributePersonBiographyFragment(): PersonBiographyFragment

    @Module
    companion object {

        @PersonScope
        @Provides
        @JvmStatic
        internal fun providePersonGetByIdUseCase(
                schedulers: Schedulers,
                gateway: Gateway
        ): PersonGetByIdUseCase {
            return PersonGetByIdUseCase(schedulers, gateway)
        }

        @PersonScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
                context: Context,
                personGetByIdUseCase: PersonGetByIdUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(PersonDetailViewModel::class.java) ->
                            PersonDetailViewModel(context, personGetByIdUseCase) as T
                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }

    }
}