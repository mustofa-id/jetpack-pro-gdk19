package id.mustofa.app.amber.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.mustofa.app.amber.LiveDataTestUtil
import id.mustofa.app.amber.MainCoroutineRule
import id.mustofa.app.amber.data.FakeMovieData
import id.mustofa.app.amber.data.source.FakeMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/09/19
 */
@ExperimentalCoroutinesApi
class TvshowFavoriteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvshowFavoriteViewModel
    private lateinit var movieRepository: FakeMovieRepository

    private val fakeTvshows = FakeMovieData.getTvshows()

    @Before
    fun setup() {
        movieRepository = FakeMovieRepository()
        movieRepository.addLocalMovies(*fakeTvshows.toTypedArray())

        viewModel = TvshowFavoriteViewModel(movieRepository)
    }

    @Test
    fun `get all tvshow favorites`() {
        viewModel.fetchMovies()
        assertNotNull(LiveDataTestUtil.getValue(viewModel.movies))
        assertEquals(fakeTvshows.size, LiveDataTestUtil.getValue(viewModel.movies).size)
    }

    @Test
    fun `get tvshow loading`() {
        mainCoroutineRule.pauseDispatcher()
        viewModel.fetchMovies()
        assertTrue(LiveDataTestUtil.getValue(viewModel.loading))

        mainCoroutineRule.resumeDispatcher()
        assertFalse(LiveDataTestUtil.getValue(viewModel.loading))
    }
}