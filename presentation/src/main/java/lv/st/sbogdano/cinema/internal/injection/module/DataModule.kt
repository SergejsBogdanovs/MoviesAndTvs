package lv.st.sbogdano.cinema.internal.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.data.BuildConfig
import lv.st.sbogdano.data.gateway.GatewayImpl
import lv.st.sbogdano.data.local.*
import lv.st.sbogdano.data.local.dao.*
import lv.st.sbogdano.data.local.database.CinemaDatabase
import lv.st.sbogdano.data.remote.*
import lv.st.sbogdano.data.remote.api.CinemaApi
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.repository.*
import lv.st.sbogdano.data.repository.mapper.*
import lv.st.sbogdano.domain.gateway.Gateway
import javax.inject.Singleton

@Module
internal class DataModule {

    // -------------------------------------MAIN-----------------------------------------------------//
    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): CinemaDatabase = CinemaDatabase.newInstance(context)

    @Provides
    @Singleton
    internal fun provideCinemaService(): CinemaService = CinemaApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideGateway(
            movieRepository: MovieRepository,
            tvRepository: TvRepository,
            creditsRepository: CreditsRepository,
            videosRepository: VideosRepository,
            reviewsRepository: ReviewsRepository,
            favoritesRepository: FavoritesRepository,
            personRepository: PersonRepository,
            movieCreditsRepository: MovieCreditsRepository
    ): Gateway {
        return GatewayImpl(
                movieRepository,
                tvRepository,
                creditsRepository,
                videosRepository,
                reviewsRepository,
                favoritesRepository,
                personRepository,
                movieCreditsRepository)
    }

    // -------------------------------------DAOs-----------------------------------------------------//
    @Provides
    @Singleton
    internal fun provideMovieDao(cinemaDatabase: CinemaDatabase): MoviesDao = cinemaDatabase.moviesDao()

    @Provides
    @Singleton
    internal fun provideTvDao(cinemaDatabase: CinemaDatabase): TvsDao = cinemaDatabase.tvsDao()

    @Provides
    @Singleton
    internal fun provideCreditsDao(cinemaDatabase: CinemaDatabase): CreditsDao = cinemaDatabase.creditsDao()

    @Provides
    @Singleton
    internal fun provideVideosDao(cinemaDatabase: CinemaDatabase): VideosDao = cinemaDatabase.videosDao()

    @Provides
    @Singleton
    internal fun provideReviewsDao(cinemaDatabase: CinemaDatabase): ReviewsDao = cinemaDatabase.reviewsDao()

    @Provides
    @Singleton
    internal fun provideFavoritesDao(cinemaDatabase: CinemaDatabase): FavoritesDao = cinemaDatabase.favoritesDao()

    @Provides
    @Singleton
    internal fun providePersonsDao(cinemaDatabase: CinemaDatabase): PersonsDao = cinemaDatabase.personsDao()

    @Provides
    @Singleton
    internal fun provideMovieCreditsDao(cinemaDatabase: CinemaDatabase): MovieCreditsDao = cinemaDatabase.movieCreditsDao()

    // -------------------------------------LOCAL DATA SOURCES---------------------------------------//
    @Provides
    @Singleton
    internal fun provideMovieLocalDataSource(moviesDao: MoviesDao): MovieLocalDataSource {
        return MovieLocalDataSource(moviesDao)
    }

    @Provides
    @Singleton
    internal fun provideTvLocalDataSource(tvsDao: TvsDao): TvLocalDataSource {
        return TvLocalDataSource(tvsDao)
    }

    @Provides
    @Singleton
    internal fun provideCreditsLocalDataSource(creditsDao: CreditsDao): CreditsLocalDataSource {
        return CreditsLocalDataSource(creditsDao)
    }

    @Provides
    @Singleton
    internal fun provideVideosLocalDataSource(videosDao: VideosDao): VideosLocalDataSource {
        return VideosLocalDataSource(videosDao)
    }

    @Provides
    @Singleton
    internal fun provideReviewsLocalDataSource(reviewsDao: ReviewsDao): ReviewsLocalDataSource {
        return ReviewsLocalDataSource(reviewsDao)
    }

    @Provides
    @Singleton
    internal fun provideFavoritesLocalDataSource(favoritesDao: FavoritesDao): FavoritesLocalDataSource {
        return FavoritesLocalDataSource(favoritesDao)
    }

    @Provides
    @Singleton
    internal fun providePersonsLocalDataSource(personsDao: PersonsDao): PersonLocalDataSource {
        return PersonLocalDataSource(personsDao)
    }

    @Provides
    @Singleton
    internal fun provideMovieCreditsLocalDataSource(movieCreditsDao: MovieCreditsDao): MovieCreditsLocalDataSource {
        return MovieCreditsLocalDataSource(movieCreditsDao)
    }

    // -------------------------------------REMOTE DATA SOURCES--------------------------------------//
    @Provides
    @Singleton
    internal fun provideMovieRemoteDataSource(cinemaService: CinemaService): MovieRemoteDataSource {
        return MovieRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideTvRemoteDataSource(cinemaService: CinemaService): TvRemoteDataSource {
        return TvRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideCreditsRemoteDataSource(cinemaService: CinemaService): CreditsRemoteDataSource {
        return CreditsRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideVideosRemoteDataSource(cinemaService: CinemaService): VideosRemoteDataSource {
        return VideosRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideReviewsRemoteDataSource(cinemaService: CinemaService): ReviewsRemoteDataSource {
        return ReviewsRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun providePersonsRemoteDataSource(cinemaService: CinemaService): PersonRemoteDataSource {
        return PersonRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideMovieCreditsRemoteDataSource(cinemaService: CinemaService): MovieCreditsRemoteDataSource {
        return MovieCreditsRemoteDataSource(cinemaService)
    }

    // -------------------------------------REPOSITORIES---------------------------------------------//
    @Provides
    @Singleton
    internal fun provideMovieRepository(
            movieLocalDataSource: MovieLocalDataSource,
            movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepository(movieLocalDataSource, movieRemoteDataSource, MovieListMapper())
    }

    @Provides
    @Singleton
    internal fun provideTvRepository(
            tvLocalDataSource: TvLocalDataSource,
            tvRemoteDataSource: TvRemoteDataSource
    ): TvRepository {
        return TvRepository(tvLocalDataSource, tvRemoteDataSource, TvListMapper())
    }

    @Provides
    @Singleton
    internal fun provideCreditsRepository(
            creditsLocalDataSource: CreditsLocalDataSource,
            creditsRemoteDataSource: CreditsRemoteDataSource
    ): CreditsRepository {
        return CreditsRepository(creditsLocalDataSource, creditsRemoteDataSource, CreditsListMapper())
    }

    @Provides
    @Singleton
    internal fun provideVideosRepository(
            videosLocalDataSource: VideosLocalDataSource,
            videosRemoteDataSource: VideosRemoteDataSource
    ): VideosRepository {
        return VideosRepository(videosLocalDataSource, videosRemoteDataSource, VideosListMapper())
    }

    @Provides
    @Singleton
    internal fun provideReviewsRepository(
            reviewsLocalDataSource: ReviewsLocalDataSource,
            reviewsRemoteDataSource: ReviewsRemoteDataSource
    ): ReviewsRepository {
        return ReviewsRepository(reviewsLocalDataSource, reviewsRemoteDataSource, ReviewsListMapper())
    }

    @Provides
    @Singleton
    internal fun provideFavoritesRepository(
            favoritesLocalDataSource: FavoritesLocalDataSource
    ): FavoritesRepository {
        return FavoritesRepository(favoritesLocalDataSource)
    }

    @Provides
    @Singleton
    internal fun providePersonRepository(
            personLocalDataSource: PersonLocalDataSource,
            personRemoteDataSource: PersonRemoteDataSource
    ): PersonRepository {
        return PersonRepository(personLocalDataSource, personRemoteDataSource, PersonMapper())
    }

    @Provides
    @Singleton
    internal fun provideMovieCreditsRepository(
            movieCreditsLocalDataSource: MovieCreditsLocalDataSource,
            movieCreditsRemoteDataSource: MovieCreditsRemoteDataSource
    ): MovieCreditsRepository {
        return MovieCreditsRepository(movieCreditsLocalDataSource, movieCreditsRemoteDataSource, MovieCreditsListMapper())
    }
}