package lv.st.sbogdano.data.gateway

import io.reactivex.Observable
import lv.st.sbogdano.data.gateway.mapper.GatewayMapper
import lv.st.sbogdano.data.repository.*
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.*

class GatewayImpl(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository,
    private val creditsRepository: CreditsRepository,
    private val videosRepository: VideosRepository,
    private val reviewsRepository: ReviewsRepository,
    private val favoritesRepository: FavoritesRepository
) : Gateway {

    private val mapper = GatewayMapper()

    override fun getMoviesByType(type: String, refresh: Boolean): Observable<List<MovieDomainModel>> =
            movieRepository.getAll(type, refresh)
                    .doOnError { println("Movies by Type($type) error") }
                    .map { it.map { movieLocalModel -> mapper.toDomainModel(movieLocalModel) } }

    override fun getTvsByType(type: String, refresh: Boolean): Observable<List<TvDomainModel>> =
            tvRepository.getAll(type, refresh)
                    .doOnError { println("TvDomainModel by Type($type) error") }
                    .map { it.map { tvLocalModel -> mapper.toDomainModel(tvLocalModel) } }

    override fun getMovieById(id: Int): Observable<MovieDomainModel> =
            movieRepository.getById(id)
                    .doOnError { println("MovieDomainModel by Id($id) Error") }
                    .map { mapper.toDomainModel(it) }

    override fun getTvById(id: Int): Observable<TvDomainModel> =
            tvRepository.getById(id)
                    .doOnError { println("TvDomainModel by Id($id) Error") }
                    .map { mapper.toDomainModel(it) }

    override fun getCreditsById(params: Pair<Int, String>): Observable<List<CreditDomainModel>> =
            creditsRepository.getAllById(params)
                    .doOnError { println("Credits by Id(${params.first}) Error") }
                    .map { it.map { creditLocalModel -> mapper.toDomainModel(creditLocalModel) } }

    override fun getVideosById(params: Pair<Int, String>): Observable<List<VideoDomainModel>> =
            videosRepository.getAllById(params)
                    .doOnError { println("Videos by Id(${params.first}) Error") }
                    .map { it.map { videoLocalModel -> mapper.toDomainModel(videoLocalModel) } }

    override fun getReviewsById(params: Pair<Int, String>): Observable<List<ReviewDomainModel>> =
            reviewsRepository.getAllById(params)
                    .doOnError { println("Reviews by Id(${params.first}) Error") }
                    .map { it.map { reviewLocalModel -> mapper.toDomainModel(reviewLocalModel) } }

    override fun addToFavorites(params: FavoriteDomainModel): Observable<Long> =
            favoritesRepository.addToFavorites(mapper.toLocalModel(params))
                    .doOnError { println("Error while adding movie to favorites") }

    override fun getFavoritesByType(type: String): Observable<List<FavoriteDomainModel>> =
            favoritesRepository.getAll(type)
                    .doOnError { println("FavoritesDomainModel by Type($type) error") }
                    .map { it.map { favoriteLocalModel -> mapper.toDomainModel(favoriteLocalModel) } }
}