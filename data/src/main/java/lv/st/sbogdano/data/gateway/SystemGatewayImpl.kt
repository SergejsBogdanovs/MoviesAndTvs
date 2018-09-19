package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.SystemMapper
import lv.st.sbogdano.data.repository.GenreTypeRepository
import lv.st.sbogdano.domain.entity.GenreType
import lv.st.sbogdano.domain.gateway.SystemGateway

class SystemGatewayImpl(private val genreTypeRepository: GenreTypeRepository) : SystemGateway {

    private val mapper = SystemMapper()

    override fun getGenreTypes(): Observable<List<GenreType>> =

            genreTypeRepository.getAll()
                    .doOnError { println("Genre type error") }
                    .map { it.map { mapper.toEntity(it) } }

}