package id.mustofa.app.amber.ui.tvshow

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
class TvshowViewModelTest {

    private lateinit var viewModel: TvshowViewModel

    @Before
    fun setup() {
        viewModel = TvshowViewModel()
    }

    @Test
    fun tvshows_Should_Not_Be_Null() {
        val tvshows = viewModel.getTvshows()
        assertNotNull(tvshows)
    }

    @Test
    fun tvshows_Count_Should_Be_10() {
        val tvshows = viewModel.getTvshows()
        assertTrue(tvshows.size == 10)
    }
}