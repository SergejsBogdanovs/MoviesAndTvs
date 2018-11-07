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

    override fun getCreditsById(id: Int): Observable<List<Credit>> =
            creditsRepository.getAllById(id)
                    .doOnError { println("Credits by Id($id) Error") }
                    .map { it.map { creditLocalModel -> mapper.toEntity(creditLocalModel) } }

    override fun getVideosById(id: Int): Observable<List<Video>> =
            videosRepository.getAllById(id)
                    .doOnError { println("Videos by Id($id) Error") }
                    .map { it.map { videoLocalModel -> mapper.toEntity(videoLocalModel) } }

    override fun getReviewsById(id: Int): Observable<List<Review>> =
            reviewsRepository.getAllById(id)
                    .doOnError { println("Reviews by Id($id) Error") }
                    .map { it.map { reviewLocalModel -> mapper.toEntity(reviewLocalModel) } }
}