package id.mustofa.app.amber.ui.movie

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
        movieRepository.addMovies(*fakeMovies.toTypedArray())

        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun `get all movies`() {
        viewModel.fetchAllMovies()

        val liveData = viewModel.allMovies
        assertNotNull(LiveDataTestUtil.getValue(liveData))
        assertEquals(LiveDataTestUtil.getValue(liveData), fakeMovies)
    }

    @Test
    fun `get error movies`() {
        movieRepository.shouldReturnError = true

        viewModel.fetchAllMovies(true)

        assertTrue(LiveDataTestUtil.getValue(viewModel.allMovies).isEmpty())
        assertEquals(LiveDataTestUtil.getValue(viewModel.message), R.string.msg_something_wrong)
    }

    @Test
    fun `get all movies and loading`() {
        mainCoroutineRule.pauseDispatcher()
        viewModel.fetchAllMovies()
        assertTrue(LiveDataTestUtil.getValue(viewModel.loading))

        mainCoroutineRule.resumeDispatcher()
        assertFalse(LiveDataTestUtil.getValue(viewModel.loading))
    }
}