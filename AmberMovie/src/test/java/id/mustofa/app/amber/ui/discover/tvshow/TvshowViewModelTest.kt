package id.mustofa.app.amber.ui.discover.tvshow

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
class TvshowViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvshowViewModel
    private lateinit var movieRepository: FakeMovieRepository

    private val fakeTvshows = FakeMovieData.getTvshows()

    @Before
    fun setup() {
        movieRepository = FakeMovieRepository()
        movieRepository.addNetworkMovies(*fakeTvshows.toTypedArray())

        viewModel = TvshowViewModel(movieRepository)
    }

    @Test
    fun `get all tvshows`() {
        viewModel.fetchMovies()

        val liveData = viewModel.movies
        assertNotNull(LiveDataTestUtil.getValue(liveData))
        assertEquals(LiveDataTestUtil.getValue(liveData), fakeTvshows)
    }

    @Test
    fun `get error tvshows`() {
        movieRepository.shouldReturnError = true

        viewModel.fetchMovies(true)

        assertTrue(LiveDataTestUtil.getValue(viewModel.movies).isEmpty())
        assertEquals(LiveDataTestUtil.getValue(viewModel.message), R.string.msg_something_wrong)
    }

    @Test
    fun `get all tvshows and loading`() {
        mainCoroutineRule.pauseDispatcher()
        viewModel.fetchMovies()
        assertTrue(LiveDataTestUtil.getValue(viewModel.loading))

        mainCoroutineRule.resumeDispatcher()
        assertFalse(LiveDataTestUtil.getValue(viewModel.loading))
    }
}