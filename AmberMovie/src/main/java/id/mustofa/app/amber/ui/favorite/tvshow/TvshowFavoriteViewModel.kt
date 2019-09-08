package id.mustofa.app.amber.ui.favorite.tvshow

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
class TvshowFavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val tvshowsResult = MutableLiveData<Result<DataSource.Factory<Int, Movie>>>()

    val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(tvshowsResult) {
        if (it is Result.Success) it.data?.let { factory ->
            val config = PagedList.Config
                .Builder()
                .setPageSize(5)
                .setEnablePlaceholders(false)
                .build()
            LivePagedListBuilder(factory, config).build()
        } else null
    }

    val message: LiveData<Int> = Transformations.map(tvshowsResult) {
        if (it is Result.Error) it.message else 0
    }

    val empty: LiveData<Boolean> = Transformations.map(movies) { it.isEmpty() }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchMovies(force: Boolean = false) {
        if (force) tvshowsResult.value = null
        _loading.value = true
        viewModelScope.launch {
            tvshowsResult.value = movieRepository.getTvshowFavorites()
            _loading.value = false
        }
    }
}