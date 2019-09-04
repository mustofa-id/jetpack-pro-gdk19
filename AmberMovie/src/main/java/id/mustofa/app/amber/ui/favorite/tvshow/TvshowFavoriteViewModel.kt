package id.mustofa.app.amber.ui.favorite.tvshow

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
class TvshowFavoriteViewModel(
    private val movieRepository: MovieRepository
) : FavoriteViewModel, ViewModel() {

    private val tvshowsResult = MutableLiveData<Result<DataSource.Factory<Int, Movie>>>()

    override val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(tvshowsResult) {
        if (it is Result.Success) it.data?.let { factory ->
            LivePagedListBuilder(factory, 5).build()
        } else MutableLiveData<PagedList<Movie>>()
    }

    override val message: LiveData<Int> = Transformations.map(tvshowsResult) {
        if (it is Result.Error) it.message else null
    }

    override val empty: LiveData<Boolean> = Transformations.map(movies) { it.isEmpty() }

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean> get() = _loading

    override fun fetchMovies(force: Boolean) {
        if (force) tvshowsResult.value = null
        _loading.value = true
        viewModelScope.launch {
            tvshowsResult.value = movieRepository.getTvshowFavorites()
            _loading.value = false
        }
    }
}