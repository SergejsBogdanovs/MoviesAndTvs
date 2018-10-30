package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.CreditsRepository
import lv.st.sbogdano.data.repository.MovieRepository
import lv.st.sbogdano.data.repository.TvRepository
import lv.st.sbogdano.data.repository.VideosRepository
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Tv
import lv.st.sbogdano.domain.entity.Video
import lv.st.sbogdano.domain.gateway.Gateway

class GatewayImpl(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val creditsRepository: CreditsRepository,
    private val videosRepository: VideosRepository
) : Gateway {

    private val mapper = GatewayMapper()

    override fun getMoviesByType(type: String, refresh: Boolean): Observable<List<Movie>> =
            movieRepository.getAll(type, refresh)
                    .doOnError { println("Movies by Type($type) error") }
                    .map { it.map { movieLocalModel -> mapper.toEntity(movieLocalModel) } }

    override fun getTvByType(type: String, refresh: Boolean): Observable<List<Tv>> =
            tvRepository.getAll(type, refresh)
                    .doOnError { println("Tv by Type($type) error") }
                    .map { it.map { tvLocalModel -> mapper.toEntity(tvLocalModel) } }

    override fun getMovieById(id: Int): Observable<Movie> =
            movieRepository.getById(id)
                    .doOnError { println("Movie by Id($id) Error") }
                    .map { mapper.toEntity(it) }

    override fun getCreditsById(id: Int): Observable<List<Credit>> =
            creditsRepository.getAllById(id)
                    .doOnError { println("Credits by Id($id) Error") }
                    .map { it.map { creditLocalModel -> mapper.toEntity(creditLocalModel) } }

    override fun getVideosById(id: Int): Observable<List<Video>> =
            videosRepository.getAllById(id)
                    .doOnError { println("Videos by Id($id) Error") }
                    .map { it.map { videoLocalModel -> mapper.toEntity(videoLocalModel) } }
}