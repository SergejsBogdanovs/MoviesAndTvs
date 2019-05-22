package lv.st.sbogdano.cinema.internal.injection.module.tv

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.TvScope
import lv.st.sbogdano.cinema.tv.detail.TvDetailViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.*

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
        ): TvGetByIdObservableUseCase {
            return TvGetByIdObservableUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideCreditsGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): CreditsGetByIdObservableUseCase {
            return CreditsGetByIdObservableUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideVideosGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): VideosGetByIdObservableUseCase {
            return VideosGetByIdObservableUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideReviewsGetByIdAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): ReviewGetByIdObservableUseCase {
            return ReviewGetByIdObservableUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideAddToFavoritesUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): AddToFavoritesUseCase {
            return AddToFavoritesUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideGetFavoriteByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): GetFavoriteByIdObservableUseCase {
            return GetFavoriteByIdObservableUseCase(schedulers, gateway)
        }

        @TvScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
            context: Context,
            tvGetByIdUseCase: TvGetByIdObservableUseCase,
            addToFavoritesUseCase: AddToFavoritesUseCase,
            creditsGetByIdUseCase: CreditsGetByIdObservableUseCase,
            videosGetByIdUseCase: VideosGetByIdObservableUseCase,
            reviewGetByIdUseCase: ReviewGetByIdObservableUseCase,
            getFavoriteByIdUseCase: GetFavoriteByIdObservableUseCase
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(TvDetailViewModel::class.java) ->
                            TvDetailViewModel(
                                    context,
                                    tvGetByIdUseCase,
                                    addToFavoritesUseCase,
                                    creditsGetByIdUseCase,
                                    videosGetByIdUseCase,
                                    reviewGetByIdUseCase,
                                    getFavoriteByIdUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }
}