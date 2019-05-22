package lv.st.sbogdano.cinema.internal.injection.module.movie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.cinema.internal.injection.scope.MovieScope
import lv.st.sbogdano.cinema.movie.detail.MovieDetailViewModel
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.*

@Module
internal abstract class MovieModule {

    @Module
    companion object {

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideMovieGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): MovieGetByIdObservableUseCase {
            return MovieGetByIdObservableUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideCreditsGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): CreditsGetByIdObservableUseCase {
            return CreditsGetByIdObservableUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideVideosGetByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): VideosGetByIdObservableUseCase {
            return VideosGetByIdObservableUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideReviewsGetByIdAllUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): ReviewGetByIdObservableUseCase {
            return ReviewGetByIdObservableUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideAddToFavoritesUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): AddToFavoritesUseCase {
            return AddToFavoritesUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideGetFavoriteByIdUseCase(
            schedulers: Schedulers,
            gateway: Gateway
        ): GetFavoriteByIdObservableUseCase {
            return GetFavoriteByIdObservableUseCase(schedulers, gateway)
        }

        @MovieScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(
            context: Context,
            movieGetByIdUseCase: MovieGetByIdObservableUseCase,
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
                        modelClass.isAssignableFrom(MovieDetailViewModel::class.java) ->
                            MovieDetailViewModel(
                                    context,
                                    movieGetByIdUseCase,
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