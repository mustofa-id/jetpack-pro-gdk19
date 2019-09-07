package id.mustofa.app.amber.ui.discover.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.mustofa.app.amber.LiveDataTestUtil
import id.mustofa.app.amber.MainCoroutineRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.FakeMovieData
import id.mustofa.app.amber.data.source.FakeMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepository: FakeMovieRepository

    private val fakeMovies = FakeMovieData.getMovies()

    @Before
    fun setup() {
        movieRepository = FakeMovieRepository()
        movieRepository.addNetworkMovies(*fakeMovies.toTypedArray())

        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun `get all movies`() {
        viewModel.fetchMovies()

        val liveData = viewModel.movies
        assertNotNull(LiveDataTestUtil.getValue(liveData))
        assertEquals(LiveDataTestUtil.getValue(liveData), fakeMovies)
    }

    @Test
    fun `get error movies`() {
        movieRepository.shouldReturnError = true

        viewModel.fetchMovies(true)

        assertTrue(LiveDataTestUtil.getValue(viewModel.movies).isEmpty())
        assertEquals(LiveDataTestUtil.getValue(viewModel.message), R.string.msg_something_wrong)
    }

    @Test
    fun `get all movies and loading`() {
        mainCoroutineRule.pauseDispatcher()
        viewModel.fetchMovies()
        assertTrue(LiveDataTestUtil.getValue(viewModel.loading))

        mainCoroutineRule.resumeDispatcher()
        assertFalse(LiveDataTestUtil.getValue(viewModel.loading))
    }
}