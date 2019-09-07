package id.mustofa.app.amber.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.LivePagedListBuilder
import id.mustofa.app.amber.LiveDataTestUtil
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.FakeMovieData
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.local.FakeMovieLocalDataSource
import id.mustofa.app.amber.data.source.remote.FakeMovieRemoteDataSource
import id.mustofa.app.amber.getPagedConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 19/08/19
 */
@ExperimentalCoroutinesApi
class DefaultMovieRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieRemoteDataSource: FakeMovieRemoteDataSource
    private lateinit var movieLocalDataSource: FakeMovieLocalDataSource
    private lateinit var movieRepository: DefaultMovieRepository

    private val fakeMovies = FakeMovieData.getMovies()
    private val fakeTvshows = FakeMovieData.getTvshows()

    private val movie = fakeMovies[1]
    private val movieId = movie.id
    private val tvshow = fakeTvshows[0]
    private val tvshowId = tvshow.id

    @Before
    fun setup() {
        movieRemoteDataSource = FakeMovieRemoteDataSource(fakeMovies, fakeTvshows)
        movieLocalDataSource = FakeMovieLocalDataSource((fakeMovies + fakeTvshows).toMutableList())

        movieRepository = DefaultMovieRepository(
            movieRemoteDataSource,
            movieLocalDataSource,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `get all movies`() = runBlockingTest {
        val result = movieRepository.getAllMovies() as Success
        assertEquals(result.data?.results, fakeMovies)
    }

    @Test
    fun `get all tvshows`() = runBlockingTest {
        val result = movieRepository.getAllTvshow() as Success
        assertEquals(result.data?.results, fakeTvshows)
    }

    @Test
    fun `get movie by id`() = runBlockingTest {
        val result = movieRepository.getMovieById(movieId) as Success
        assertEquals(result.data, movie)
    }

    @Test
    fun `get tvshows by id`() = runBlockingTest {
        val result = movieRepository.getTvshowById(tvshowId) as Success
        assertEquals(result.data, tvshow)
    }

    @Test
    fun `get error movie by id`() = runBlockingTest {
        val result = movieRepository.getMovieById(1111) as Error
        assertEquals(result.message, R.string.msg_not_found)
    }

    @Test
    fun `get error tvshow by id`() = runBlockingTest {
        val result = movieRepository.getTvshowById(1212) as Error
        assertEquals(result.message, R.string.msg_not_found)
    }

    @Test
    fun `get movie favorites`() = runBlockingTest {
        val result = movieRepository.getMovieFavorites() as Success
        val livePagedList = LivePagedListBuilder(
            result.data!!, getPagedConfig(fakeMovies.size)
        ).build()
        val pagedList = LiveDataTestUtil.getValue(livePagedList)

        assertNotNull(pagedList)
        assertEquals(fakeMovies.size, pagedList?.size)
    }

    @Test
    fun `get tvshow favorites`() = runBlockingTest {
        val result = movieRepository.getTvshowFavorites() as Success
        val livePagedList = LivePagedListBuilder(
            result.data!!, getPagedConfig(fakeTvshows.size)
        ).build()
        val pagedList = LiveDataTestUtil.getValue(livePagedList)

        assertNotNull(pagedList)
        assertEquals(fakeTvshows.size, pagedList?.size)
    }

    @Test
    fun `get favorite by id`() = runBlockingTest {
        val result = movieRepository.getFavoriteById(movieId) as Success
        assertNotNull(result.data)
    }

    @Test
    fun `add to favorite`() = runBlockingTest {
        val result = movieRepository.addMovieToFavorite(movie) as Success
        assertEquals(movieId, result.data!!)
    }

    @Test
    fun `remove from favorite`() = runBlockingTest {
        val result = movieRepository.removeMovieFromFavorite(movieId) as Success
        assertTrue(result.data!! > 0)
    }

    @Test
    fun `is movie in favorite`() = runBlockingTest {
        val favorite = movieRepository.isInFavorite(movieId)
        assertTrue(favorite)
    }
}