package id.mustofa.app.amber.ui.favorite

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 04/09/19
 */
interface FavoriteViewModel {

    val movies: LiveData<PagedList<Movie>>
    val message: LiveData<Int>
    val empty: LiveData<Boolean>
    val loading: LiveData<Boolean>

    fun fetchMovies(force: Boolean = false)
}