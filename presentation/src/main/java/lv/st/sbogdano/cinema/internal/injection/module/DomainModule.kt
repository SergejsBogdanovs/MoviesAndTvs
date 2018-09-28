package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.Provides
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    internal fun provideMoviesByTypeGetAllUseCase(schedulers: Schedulers,
                                               gateway: Gateway): MoviesByTypeGetAllUseCase {
        return MoviesByTypeGetAllUseCase(schedulers, gateway)
    }
}