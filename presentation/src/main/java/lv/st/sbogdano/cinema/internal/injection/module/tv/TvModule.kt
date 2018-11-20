package lv.st.sbogdano.cinema.internal.injection.module.tv

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.TvScope
import lv.st.sbogdano.cinema.tv.detail.TvDetailViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdUseCase
import lv.st.sbogdano.domain.interactor.TvGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase

@Module
internal abstract class TvModule {

    @Module
    companion object {

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideTvGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): TvGetByIdUseCase {
            return TvGetByIdUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideCreditsGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): CreditsGetByIdUseCase {
            return CreditsGetByIdUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideVideosGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): VideosGetByIdUseCase {
            return VideosGetByIdUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideReviewsGetByIdAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): ReviewGetByIdUseCase {
            return ReviewGetByIdUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
            context: Context,
            tvGetByIdUseCase: TvGetByIdUseCase,
            creditsGetByIdUseCase: CreditsGetByIdUseCase,
            videosGetByIdUseCase: VideosGetByIdUseCase,
            reviewGetByIdUseCase: ReviewGetByIdUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(TvDetailViewModel::class.java) ->
                            TvDetailViewModel(
                                    context,
                                    tvGetByIdUseCase,
                                    creditsGetByIdUseCase,
                                    videosGetByIdUseCase,
                                    reviewGetByIdUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}