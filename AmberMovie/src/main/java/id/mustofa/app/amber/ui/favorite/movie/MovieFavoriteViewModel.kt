package id.mustofa.app.amber.ui.favorite.movie

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.MovieRepository
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class MovieFavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val moviesResult = MutableLiveData<Result<DataSource.Factory<Int, Movie>>>()

    val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(moviesResult) {
        if (it is Result.Success) it.data?.let { factory ->
            val config = PagedList.Config
                .Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .build()
            LivePagedListBuilder(factory, config).build()
        } else null
    }

    val message: LiveData<Int> = Transformations.map(moviesResult) {
        if (it is Result.Error) it.message else 0
    }

    val empty: LiveData<Boolean> = Transformations.map(movies) { it.isEmpty() }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchMovies(force: Boolean = false) {
        if (force) moviesResult.value = null
        _loading.value = true
        viewModelScope.launch {
            moviesResult.value = movieRepository.getMovieFavorites()
            _loading.value = false
        }
    }
}