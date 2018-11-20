package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.*
import lv.st.sbogdano.domain.entity.*
import lv.st.sbogdano.domain.gateway.Gateway

class GatewayImpl(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val creditsRepository: CreditsRepository,
    private val videosRepository: VideosRepository,
    private val reviewsRepository: ReviewsRepository
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

    override fun getTvById(id: Int): Observable<Tv> =
            tvRepository.getById(id)
                    .doOnError { println("Tv by Id($id) Error") }
                    .map { mapper.toEntity(it) }

    override fun getCreditsById(params: Pair<Int, String>): Observable<List<Credit>> =
            creditsRepository.getAllById(params)
                    .doOnError { println("Credits by Id(${params.first}) Error") }
                    .map { it.map { creditLocalModel -> mapper.toEntity(creditLocalModel) } }

    override fun getVideosById(params: Pair<Int, String>): Observable<List<Video>> =
            videosRepository.getAllById(params)
                    .doOnError { println("Videos by Id(${params.first}) Error") }
                    .map { it.map { videoLocalModel -> mapper.toEntity(videoLocalModel) } }

    override fun getReviewsById(params: Pair<Int, String>): Observable<List<Review>> =
            reviewsRepository.getAllById(params)
                    .doOnError { println("Reviews by Id(${params.first}) Error") }
                    .map { it.map { reviewLocalModel -> mapper.toEntity(reviewLocalModel) } }
}