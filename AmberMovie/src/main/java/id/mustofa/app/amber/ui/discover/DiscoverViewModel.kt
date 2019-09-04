package id.mustofa.app.amber.ui.discover

import androidx.lifecycle.LiveData
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 04/09/19
 */
interface DiscoverViewModel {

    val movies: LiveData<List<Movie>>
    val loading: LiveData<Boolean>
    val message: LiveData<Int>
    val empty: LiveData<Boolean>

    fun fetchMovies(force: Boolean = false)
}