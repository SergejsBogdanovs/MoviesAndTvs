package lv.st.sbogdano.domain.interactor

import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway

class FavoritesByTypeGetAllUseCase(
        schedulers: Schedulers,
        private val gateway: Gateway
)