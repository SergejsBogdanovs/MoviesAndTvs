package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.Provides
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.*
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    internal fun provideMoviesByTypeGetAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MoviesByTypeGetAllObservableUseCase {
        return MoviesByTypeGetAllObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideMovieGetByIdUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MovieGetByIdObservableUseCase {
        return MovieGetByIdObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideTvByTypeGetAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): TvsByTypeGetAllObservableUseCase {
        return TvsByTypeGetAllObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideCreditsGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): CreditsGetByIdObservableUseCase {
        return CreditsGetByIdObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideVideosGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): VideosGetByIdObservableUseCase {
        return VideosGetByIdObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideReviewsGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): ReviewGetByIdObservableUseCase {
        return ReviewGetByIdObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideAddToFavoritesUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): AddToFavoritesUseCase {
        return AddToFavoritesUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideFavoritesByTypeUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): FavoritesByTypeGetAllObservableUseCase {
        return FavoritesByTypeGetAllObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun providePersonsGetByIdUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): PersonGetByIdObservableUseCase {
        return PersonGetByIdObservableUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideMovieCreditsGetByIdUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MovieCreditsGetByPersonIdObservableUseCase {
        return MovieCreditsGetByPersonIdObservableUseCase(schedulers, gateway)
    }
}