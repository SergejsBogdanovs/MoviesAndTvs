package lv.st.sbogdano.cinema.movie

import android.app.Application
import android.content.Context
import io.reactivex.Observable
import lv.st.sbogdano.cinema.movie.detail.MovieDetailViewModel
import lv.st.sbogdano.cinema.movie.mapper.MovieMapper
import lv.st.sbogdano.domain.interactor.*
import lv.st.sbogdano.domain.model.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class MovieDetailTest {

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var application: Application
    @Mock
    private lateinit var movieGetByIdUseCase: MovieGetByIdObservableUseCase
    @Mock
    private lateinit var addToFavoritesUseCase: AddToFavoritesUseCase
    @Mock
    private lateinit var creditsGetByIdUseCase: CreditsGetByIdObservableUseCase
    @Mock
    private lateinit var videosGetByIdUseCase: VideosGetByIdObservableUseCase
    @Mock
    private lateinit var reviewGetByIdUseCase: ReviewGetByIdObservableUseCase
    @Mock
    private lateinit var getFavoriteByIdUseCase: GetFavoriteByIdObservableUseCase

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var mapper: MovieMapper

    private val movieId = 297761
    private val path = "movie"
    private val params = Pair(movieId, path)

    @Before
    fun setup() {
        `when`(context.applicationContext).thenReturn(application)
        movieDetailViewModel = MovieDetailViewModel(
                context,
                movieGetByIdUseCase,
                addToFavoritesUseCase,
                creditsGetByIdUseCase,
                videosGetByIdUseCase,
                reviewGetByIdUseCase,
                getFavoriteByIdUseCase)

        mapper = MovieMapper()
    }

    @Test
    @Throws(Exception::class)
    fun `Given movie item, When get movie by id, Should update result`() {

        // Given
        val movie = MovieDomainModel(
                1,
                "posterPath",
                "overview",
                "releaseDate",
                "title",
                1.0f,
                1,
                1.0f,
                "movie")

        val favorite = FavoriteDomainModel(
                1,
                "posterPath",
                "movie"
        )

        `when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.just(movie))
        `when`(getFavoriteByIdUseCase.execute(movieId)).thenReturn(Observable.just(favorite))

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        //assertThat(movieDetailViewModel.isFavorite.get(), `is`(false))
        assertThat(movieDetailViewModel.movie.get(), `is`(mapper.toModel(movie)))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load movie detail with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.error(error))
        `when`(getFavoriteByIdUseCase.execute(movieId)).thenReturn(Observable.error(error))

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load movie detail with unknown error, Should update error`() {

        // Given
        val error = "Unknown error"
        `when`(application.getString(ArgumentMatchers.anyInt())).thenReturn(error)
        `when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.error(RuntimeException()))
        `when`(getFavoriteByIdUseCase.execute(movieId)).thenReturn(Observable.error(RuntimeException()))

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

    @Test
    @Throws(Exception::class)
    fun `Given credits items, When get credits by id, Should update result`() {

        // Given
        val credits = listOf(CreditDomainModel(1, "name", "character", "profilePath"))

        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.just(credits))

        // When
        movieDetailViewModel.loadCredits(movieId, path)

        // Should
        assertThat(movieDetailViewModel.credits, `is`(credits))
    }

    @Test
    @Throws(Exception::class)
    fun `Given empty credits, When load empty credits, Should update empty`() {

        // Given
        val credits = emptyList<CreditDomainModel>()
        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.just(credits))

        // When
        movieDetailViewModel.loadCredits(movieId, path)

        // Should
        assertThat(movieDetailViewModel.empty.get(), `is`(true))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load credits with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.error(error))

        // When
        movieDetailViewModel.loadCredits(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load credits with unknown error, Should update error`() {

        // Given
        val error = "Unknown error"
        `when`(application.getString(ArgumentMatchers.anyInt())).thenReturn(error)
        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.error(RuntimeException()))

        // When
        movieDetailViewModel.loadCredits(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

    @Test
    @Throws(Exception::class)
    fun `Given videos items, When get videos by id, Should update result`() {

        // Given
        val videos = listOf(VideoDomainModel("id", "key", "name"))
        `when`(videosGetByIdUseCase.execute(params)).thenReturn(Observable.just(videos))

        // When
        movieDetailViewModel.loadVideos(movieId, path)

        // Should
        assertThat(movieDetailViewModel.video.get(), `is`(videos.first()))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load videos with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(videosGetByIdUseCase.execute(params)).thenReturn(Observable.error(error))

        // When
        movieDetailViewModel.loadVideos(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load videos with unknown error, Should update error`() {

        // Given
        val error = "Unknown error"
        `when`(application.getString(ArgumentMatchers.anyInt())).thenReturn(error)
        `when`(videosGetByIdUseCase.execute(params)).thenReturn(Observable.error(RuntimeException()))

        // When
        movieDetailViewModel.loadVideos(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

    @Test
    @Throws(Exception::class)
    fun `Given reviews items, When get reviews by id, Should update result`() {

        // Given
        val reviews = listOf(ReviewDomainModel("id", "author", "content"))

        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.just(reviews))

        // When
        movieDetailViewModel.loadReviews(movieId, path)

        // Should
        assertThat(movieDetailViewModel.reviews, `is`(reviews))
    }

    @Test
    @Throws(Exception::class)
    fun `Given empty reviews, When load empty reviews, Should update empty`() {

        // Given
        val reviews = emptyList<ReviewDomainModel>()
        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.just(reviews))

        // When
        movieDetailViewModel.loadReviews(movieId, path)

        // Should
        assertThat(movieDetailViewModel.empty.get(), `is`(true))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load reviews with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.error(error))

        // When
        movieDetailViewModel.loadReviews(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load reviews with unknown error, Should update error`() {

        // Given
        val error = "Unknown error"
        `when`(application.getString(ArgumentMatchers.anyInt())).thenReturn(error)
        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.error(RuntimeException()))

        // When
        movieDetailViewModel.loadReviews(movieId, path)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }
}