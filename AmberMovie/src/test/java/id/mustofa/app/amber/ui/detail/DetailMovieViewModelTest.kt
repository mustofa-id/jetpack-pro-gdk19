package id.mustofa.app.amber.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.mustofa.app.amber.LiveDataTestUtil
import id.mustofa.app.amber.MainCoroutineRule
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.FakeMovieData
import id.mustofa.app.amber.data.source.FakeMovieRepository
import id.mustofa.app.amber.util.MediaType
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
class DetailMovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var movieRepository: FakeMovieRepository

    private val fakeMovie = FakeMovieData.getMovies()[0]
    private val movieId = fakeMovie.id

    @Before
    fun setup() {
        movieRepository = FakeMovieRepository()
        movieRepository.addNetworkMovies(fakeMovie)

        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.type = MediaType.MOVIE
    }

    @Test
    fun `get movie and load into view`() {
        viewModel.movieId = movieId
        viewModel.fetchMovie()
        assertEquals(LiveDataTestUtil.getValue(viewModel.movie), fakeMovie)
    }

    @Test
    fun `get error movie not found`() {
        viewModel.movieId = 0
        viewModel.fetchMovie()
        assertNull(LiveDataTestUtil.getValue(viewModel.movie))
        assertEquals(LiveDataTestUtil.getValue(viewModel.message), R.string.msg_not_found)
    }

    @Test
    fun `get error movie something wrong`() {
        movieRepository.shouldReturnError = true

        viewModel.movieId = movieId
        viewModel.fetchMovie()
        assertNull(LiveDataTestUtil.getValue(viewModel.movie))
        assertEquals(LiveDataTestUtil.getValue(viewModel.message), R.string.msg_something_wrong)
    }

    @Test
    fun `get movie and loading`() {
        viewModel.movieId = movieId

        mainCoroutineRule.pauseDispatcher()
        viewModel.fetchMovie()
        assertTrue(LiveDataTestUtil.getValue(viewModel.loading))

        mainCoroutineRule.resumeDispatcher()
        assertFalse(LiveDataTestUtil.getValue(viewModel.loading))
    }

    @Test
    fun `toggle movie favorite`() {
        viewModel.movieId = movieId
        viewModel.fetchMovie()

        viewModel.toggleFavorite()
        assertEquals(LiveDataTestUtil.getValue(viewModel.favoriteIcon), R.drawable.ic_not_favorite)

        viewModel.toggleFavorite()
        assertEquals(LiveDataTestUtil.getValue(viewModel.favoriteIcon), R.drawable.ic_favorite)
    }
}