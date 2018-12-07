package lv.st.sbogdano.cinema.movie

import android.app.Application
import android.content.Context
import io.reactivex.Observable
import lv.st.sbogdano.cinema.movie.detail.MovieDetailViewModel
import lv.st.sbogdano.cinema.movie.detail.mapper.MovieMapper
import lv.st.sbogdano.domain.entity.Credit
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.entity.Review
import lv.st.sbogdano.domain.entity.Video
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase
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
    private lateinit var movieGetByIdUseCase: MovieGetByIdUseCase
    @Mock
    private lateinit var creditsGetByIdUseCase: CreditsGetByIdUseCase
    @Mock
    private lateinit var videosGetByIdUseCase: VideosGetByIdUseCase
    @Mock
    private lateinit var reviewGetByIdUseCase: ReviewGetByIdUseCase

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
                creditsGetByIdUseCase,
                videosGetByIdUseCase,
                reviewGetByIdUseCase)
        mapper = MovieMapper()
    }

    @Test
    @Throws(Exception::class)
    fun `Given movie item, When get movie by id, Should update result`() {

        // Given
        val movie = Movie(
                1,
                "posterPath",
                "overview",
                "releaseDate",
                "title",
                1.0f,
                1,
                1.0f)

        `when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.just(movie))

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        assertThat(movieDetailViewModel.movie.get(), `is`(mapper.toModel(movie)))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load movie detail with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.error(error))

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

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }


    @Test
    @Throws(Exception::class)
    fun `Given credits items, When get credits by id, Should update result`() {

        // Given
        val credits = listOf(Credit(1, "name", "character", "profilePath"))

        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.just(credits))

        // When
        movieDetailViewModel.loadCredits(movieId)

        // Should
        assertThat(movieDetailViewModel.credits, `is`(credits))
    }

    @Test
    @Throws(Exception::class)
    fun `Given empty credits, When load empty credits, Should update empty`() {

        // Given
        val credits = emptyList<Credit>()
        `when`(creditsGetByIdUseCase.execute(params)).thenReturn(Observable.just(credits))

        // When
        movieDetailViewModel.loadCredits(movieId)

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
        movieDetailViewModel.loadCredits(movieId)

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
        movieDetailViewModel.loadCredits(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

    @Test
    @Throws(Exception::class)
    fun `Given videos items, When get videos by id, Should update result`() {

        // Given
        val videos = listOf(Video("id", "key", "name"))
        `when`(videosGetByIdUseCase.execute(params)).thenReturn(Observable.just(videos))

        // When
        movieDetailViewModel.loadVideos(movieId)

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
        movieDetailViewModel.loadVideos(movieId)

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
        movieDetailViewModel.loadVideos(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

    @Test
    @Throws(Exception::class)
    fun `Given reviews items, When get reviews by id, Should update result`() {

        // Given
        val reviews = listOf(Review("id", "author", "content"))

        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.just(reviews))

        // When
        movieDetailViewModel.loadReviews(movieId)

        // Should
        assertThat(movieDetailViewModel.reviews, `is`(reviews))
    }


    @Test
    @Throws(Exception::class)
    fun `Given empty reviews, When load empty reviews, Should update empty`() {

        // Given
        val reviews = emptyList<Review>()
        `when`(reviewGetByIdUseCase.execute(params)).thenReturn(Observable.just(reviews))

        // When
        movieDetailViewModel.loadReviews(movieId)

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
        movieDetailViewModel.loadReviews(movieId)

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
        movieDetailViewModel.loadReviews(movieId)

        // Should
        assertThat(movieDetailViewModel.error.get(), `is`(error))
    }

}