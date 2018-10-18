package lv.st.sbogdano.cinema.internal.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.data.BuildConfig
import lv.st.sbogdano.data.gateway.GatewayImpl
import lv.st.sbogdano.data.local.MovieLocalDataSource
import lv.st.sbogdano.data.local.TvLocalDataSource
import lv.st.sbogdano.data.local.dao.MovieDao
import lv.st.sbogdano.data.local.dao.TvDao
import lv.st.sbogdano.data.local.database.CinemaDatabase
import lv.st.sbogdano.data.remote.MovieRemoteDataSource
import lv.st.sbogdano.data.remote.TvRemoteDataSource
import lv.st.sbogdano.data.remote.api.CinemaApi
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.repository.MovieRepository
import lv.st.sbogdano.data.repository.TvRepository
import lv.st.sbogdano.data.repository.mapper.MovieListMapper
import lv.st.sbogdano.data.repository.mapper.TvListMapper
import lv.st.sbogdano.domain.gateway.Gateway
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    internal fun provideCinemaService(): CinemaService = CinemaApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): CinemaDatabase = CinemaDatabase.newInstance(context)

    @Provides
    @Singleton
    internal fun provideMovieDao(cinemaDatabase: CinemaDatabase): MovieDao = cinemaDatabase.movieDao()

    @Provides
    @Singleton
    internal fun provideTvDao(cinemaDatabase: CinemaDatabase): TvDao = cinemaDatabase.tvDao()

    @Provides
    @Singleton
    internal fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSource(movieDao)
    }

    @Provides
    @Singleton
    internal fun provideTvLocalDataSource(tvDao: TvDao): TvLocalDataSource {
        return TvLocalDataSource(tvDao)
    }

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
    internal fun provideGateway(
        movieRepository: MovieRepository,
        tvRepository: TvRepository
    ): Gateway {
        return GatewayImpl(movieRepository, tvRepository)
    }
}