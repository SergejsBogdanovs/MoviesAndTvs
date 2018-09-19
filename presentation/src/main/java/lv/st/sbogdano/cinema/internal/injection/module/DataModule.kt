package lv.st.sbogdano.cinema.internal.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import lv.st.sbogdano.data.BuildConfig
import lv.st.sbogdano.data.gateway.SystemGatewayImpl
import lv.st.sbogdano.data.local.GenreTypeLocalDataSource
import lv.st.sbogdano.data.local.dao.GenreTypeDao
import lv.st.sbogdano.data.local.system.SystemDatabase
import lv.st.sbogdano.data.remote.GenreTypeRemoteDataSource
import lv.st.sbogdano.data.remote.api.CinemaApi
import lv.st.sbogdano.data.remote.api.CinemaService
import lv.st.sbogdano.data.repository.GenreTypeRepository
import lv.st.sbogdano.data.repository.mapper.GenreTypeMapper
import lv.st.sbogdano.domain.gateway.SystemGateway
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    internal fun provideCinemaService(): CinemaService = CinemaApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideSystemDatabase(context: Context): SystemDatabase = SystemDatabase.newInstance(context)

    @Provides
    @Singleton
    internal fun provideGenreTypeDao(systemDatabase: SystemDatabase): GenreTypeDao = systemDatabase.genreTypeDao()

    @Provides
    @Singleton
    internal fun provideGenreTypeLocalDataSource(genreTypeDao: GenreTypeDao): GenreTypeLocalDataSource {
        return GenreTypeLocalDataSource(genreTypeDao)
    }

    @Provides
    @Singleton
    internal fun provideGenreTypeRemoteDataSource(cinemaService: CinemaService): GenreTypeRemoteDataSource {
        return GenreTypeRemoteDataSource(cinemaService)
    }

    @Provides
    @Singleton
    internal fun provideGenreTypeRepository(genreTypeLocalDataSource: GenreTypeLocalDataSource,
                                            genreTypeRemoteDataSource: GenreTypeRemoteDataSource): GenreTypeRepository {
        return GenreTypeRepository(genreTypeLocalDataSource, genreTypeRemoteDataSource, GenreTypeMapper())
    }

    @Provides
    @Singleton
    internal fun provideSystemGateway(genreTypeRepository: GenreTypeRepository): SystemGateway {
        return SystemGatewayImpl(genreTypeRepository)
    }


}