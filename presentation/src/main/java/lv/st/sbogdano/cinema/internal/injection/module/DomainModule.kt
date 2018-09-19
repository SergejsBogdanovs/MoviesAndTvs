package lv.st.sbogdano.cinema.internal.injection.module

import dagger.Module
import dagger.Provides
import lv.st.sbogdano.data.gateway.SystemGatewayImpl
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.SystemGateway
import lv.st.sbogdano.domain.interactor.GenreTypeGetAllUseCase
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    internal fun provideGenreTypeGetAllUseCase(schedulers: Schedulers,
                                               systemGateWay: SystemGateway): GenreTypeGetAllUseCase {
        return GenreTypeGetAllUseCase(schedulers, systemGateWay)
    }
}