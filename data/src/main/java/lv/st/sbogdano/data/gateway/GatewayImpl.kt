package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.MovieRepository
import lv.st.sbogdano.data.repository.TvRepository
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.gateway.Gateway

class GatewayImpl(
        private val movieRepository: MovieRepository,
        private val tvRepository: TvRepository
) : Gateway {

    private val mapper = GatewayMapper()

    override fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>> =
            movieRepository.getAll(type, refresh)
                    .doOnError { println("Movies by Type($type) error") }
                    .map { it.map { mapper.toEntity(it) } }

    override fun getTvByType(type: String, refresh: Boolean): Observable<List<Tv>> =
            tvRepository.getAll(type, refresh)
                    .doOnError { println("Tv by Type($type) error") }
                    .map { it.map { mapper.toEntity(it) } }


}