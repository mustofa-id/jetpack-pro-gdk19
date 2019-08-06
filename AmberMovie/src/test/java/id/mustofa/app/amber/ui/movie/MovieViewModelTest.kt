package id.mustofa.app.amber.ui.movie

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun movies_Should_Not_Be_Null() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
    }

    @Test
    fun movies_Count_Should_Be_10() {
        val movies = viewModel.getMovies()
        assertTrue(movies.size == 10)
    }
}