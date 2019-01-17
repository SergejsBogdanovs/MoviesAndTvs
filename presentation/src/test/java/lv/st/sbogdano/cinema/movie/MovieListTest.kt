package lv.st.sbogdano.cinema.movie

import android.app.Application
import android.content.Context
import io.reactivex.Observable
import lv.st.sbogdano.cinema.movie.list.MovieListViewModel
import lv.st.sbogdano.cinema.movie.mapper.MovieMapper
import lv.st.sbogdano.domain.interactor.MoviesByTypeGetAllUseCase
import lv.st.sbogdano.domain.model.MovieDomainModel
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class MovieListTest {

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var application: Application
    @Mock
    private lateinit var moviesByTypeGetAllUseCase: MoviesByTypeGetAllUseCase

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var mMapper: MovieMapper

    private val type = "popular"
    private val refresh = true
    private val params = Pair(type, refresh)

    @Before
    fun setup() {
        `when`(context.applicationContext).thenReturn(application)
        movieListViewModel = MovieListViewModel(context, moviesByTypeGetAllUseCase)
        mMapper = MovieMapper()
    }

    @Test
    @Throws(Exception::class)
    fun `Given movies, When load movies, Should update result`() {

        // Given
        val movies = listOf(MovieDomainModel(
                1,
                "posterPath",
                "overview",
                "releaseDate",
                "title",
                1.0f,
                1,
                1.0f,
                "type"))

        `when`(moviesByTypeGetAllUseCase.execute(params)).thenReturn(Observable.just(movies))

        // When
        movieListViewModel.loadMovieList(type, true)

        // Should
        assertThat(movieListViewModel.result.first(), `is`(mMapper.toModel(movies.first())))
    }

    @Test
    @Throws(Exception::class)
    fun `Given empty list of movies, When load empty list of movies, Should update empty`() {

        // Given
        val movies = emptyList<MovieDomainModel>()
        `when`(moviesByTypeGetAllUseCase.execute(params)).thenReturn(Observable.just(movies))

        // When
        movieListViewModel.loadMovieList(type, true)

        // Should
        assertThat(movieListViewModel.empty.get(), `is`(true))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load movies with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(moviesByTypeGetAllUseCase.execute(params)).thenReturn(Observable.error(error))

        // When
        movieListViewModel.loadMovieList(type, true)

        // Should
        assertThat(movieListViewModel.error.get(), `is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load movies with unknown error, Should update error`() {

        // Given
        val error = "Unknown error"
        `when`(application.getString(anyInt())).thenReturn(error)
        `when`(moviesByTypeGetAllUseCase.execute(params)).thenReturn(Observable.error(RuntimeException()))

        // When
        movieListViewModel.loadMovieList(type, true)

        // Should
        assertThat(movieListViewModel.error.get(), `is`(error))
    }
}