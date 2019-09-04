package id.mustofa.app.amber.ui.favorite.movie

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.MovieRepository
import id.mustofa.app.amber.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class MovieFavoriteViewModel(
    private val movieRepository: MovieRepository
) : FavoriteViewModel, ViewModel() {

    private val moviesResult = MutableLiveData<Result<DataSource.Factory<Int, Movie>>>()

    override val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(moviesResult) {
        if (it is Result.Success) it.data?.let { factory ->
            LivePagedListBuilder(factory, 5).build()
        } else null
    }

    override val message: LiveData<Int> = Transformations.map(moviesResult) {
        if (it is Result.Error) it.message else null
    }

    override val empty: LiveData<Boolean> = Transformations.map(movies) { it.isEmpty() }

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean> get() = _loading

    override fun fetchMovies(force: Boolean) {
        if (force) moviesResult.value = null
        _loading.value = true
        viewModelScope.launch {
            moviesResult.value = movieRepository.getMovieFavorites()
            _loading.value = false
        }
    }
}