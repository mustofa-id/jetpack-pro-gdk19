package id.mustofa.app.amber.ui.discover.tvshow

import androidx.lifecycle.*
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result.Error
import id.mustofa.app.amber.data.Result.Success
import id.mustofa.app.amber.data.source.MovieRepository
import id.mustofa.app.amber.ui.discover.DiscoverViewModel
import kotlinx.coroutines.launch

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class TvshowViewModel(
    private val movieRepository: MovieRepository
) : DiscoverViewModel, ViewModel() {

    private val _allTvshows = MutableLiveData<List<Movie>>().apply { value = emptyList() }
    override val movies: LiveData<List<Movie>> get() = _allTvshows

    private val _message = MutableLiveData<Int>()
    override val message: LiveData<Int> get() = _message

    private val _loading = MutableLiveData<Boolean>()
    override val loading: LiveData<Boolean> get() = _loading

    override val empty: LiveData<Boolean> = Transformations.map(_allTvshows) { it.isEmpty() }

    init {
        fetchMovies()
    }

    override fun fetchMovies(force: Boolean) {
        if (force) _allTvshows.value = listOf()

        _loading.postValue(true)
        viewModelScope.launch {
            when (val result = movieRepository.getAllTvshow()) {
                is Success -> _allTvshows.postValue(result.data?.results)
                is Error -> _message.postValue(result.message)
            }
            _loading.postValue(false)
        }
    }
}