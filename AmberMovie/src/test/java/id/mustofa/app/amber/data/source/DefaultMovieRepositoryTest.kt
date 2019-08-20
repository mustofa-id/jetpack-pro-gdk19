package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.FakeMovieData
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.remote.FakeMovieRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 19/08/19
 */
@ExperimentalCoroutinesApi
class DefaultMovieRepositoryTest {

    private lateinit var movieLocalDataSource: FakeMovieRemoteDataSource
    private lateinit var movieRepository: DefaultMovieRepository

    private val fakeMovies = FakeMovieData.getMovies()
    private val fakeTvshows = FakeMovieData.getTvshows()

    private val movie = fakeMovies[1]
    private val movieId = movie.id
    private val tvshow = fakeTvshows[0]
    private val tvshowId = tvshow.id

    @Before
    fun setup() {
        movieLocalDataSource = FakeMovieRemoteDataSource(fakeMovies, fakeTvshows)
        movieRepository = DefaultMovieRepository(movieLocalDataSource, Dispatchers.Unconfined)
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
}