package lv.st.sbogdano.cinema.movie

import android.app.Application
import android.content.Context
import io.reactivex.Observable
import lv.st.sbogdano.cinema.movie.detail.MovieDetailViewModel
import lv.st.sbogdano.cinema.movie.detail.mapper.MovieMapper
import lv.st.sbogdano.domain.entity.Movie
import lv.st.sbogdano.domain.interactor.CreditsGetByIdUseCase
import lv.st.sbogdano.domain.interactor.MovieGetByIdUseCase
import lv.st.sbogdano.domain.interactor.ReviewGetByIdUseCase
import lv.st.sbogdano.domain.interactor.VideosGetByIdUseCase
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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

    @Before
    fun setup() {
        Mockito.`when`(context.applicationContext).thenReturn(application)
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

        Mockito.`when`(movieGetByIdUseCase.execute(movieId)).thenReturn(Observable.just(movie))

        // When
        movieDetailViewModel.loadMovieDetail(movieId)

        // Should
        Assert.assertThat(movieDetailViewModel.movie.get(), Is.`is`(mapper.toModel(movie)))
    }



}