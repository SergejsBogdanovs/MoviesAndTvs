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
    ): MoviesByTypeGetAllUseCase {
        return MoviesByTypeGetAllUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideMovieGetByIdUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): MovieGetByIdUseCase {
        return MovieGetByIdUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideTvByTypeGetAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): TvsByTypeGetAllUseCase {
        return TvsByTypeGetAllUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideCreditsGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): CreditsGetByIdUseCase {
        return CreditsGetByIdUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideVideosGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): VideosGetByIdUseCase {
        return VideosGetByIdUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideReviewsGetByIdAllUseCase(
        schedulers: Schedulers,
        gateway: Gateway
    ): ReviewGetByIdUseCase {
        return ReviewGetByIdUseCase(schedulers, gateway)
    }

    @Provides
    @Singleton
    internal fun provideAddToFavoritesUseCase(
            schedulers: Schedulers,
            gateway: Gateway
    ): AddToFavoritesUseCase {
        return AddToFavoritesUseCase(schedulers, gateway)
    }

}