package id.mustofa.app.amber

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.mustofa.app.amber.data.source.MovieRepository
import id.mustofa.app.amber.ui.detail.DetailMovieViewModel
import id.mustofa.app.amber.ui.discover.movie.MovieViewModel
import id.mustofa.app.amber.ui.discover.tvshow.TvshowViewModel
import id.mustofa.app.amber.ui.favorite.movie.MovieFavoriteViewModel
import id.mustofa.app.amber.ui.favorite.tvshow.TvshowFavoriteViewModel

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
class ViewModelFactory private constructor(
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var instance: ViewModelFactory? = null

        fun instance(app: Application) = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(app))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(DetailMovieViewModel::class.java) ->
                DetailMovieViewModel(movieRepository)
            isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(movieRepository)
            isAssignableFrom(TvshowViewModel::class.java) ->
                TvshowViewModel(movieRepository)
            isAssignableFrom(MovieFavoriteViewModel::class.java) ->
                MovieFavoriteViewModel(movieRepository)
            isAssignableFrom(TvshowFavoriteViewModel::class.java) ->
                TvshowFavoriteViewModel(movieRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}