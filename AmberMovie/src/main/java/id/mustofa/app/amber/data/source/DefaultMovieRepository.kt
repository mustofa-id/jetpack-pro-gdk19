package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.base.BaseRepository
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.local.MovieLocalDataSource
import id.mustofa.app.amber.data.source.remote.MovieRemoteDataSource
import id.mustofa.app.amber.data.source.remote.Movies
import id.mustofa.app.amber.util.MediaType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 *
 * This class always construct as singleton instance
 */
class DefaultMovieRepository private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    override val dispatcher: CoroutineDispatcher
) : MovieRepository, BaseRepository() {

    companion object {

        @Volatile
        private var instance: DefaultMovieRepository? = null

        operator fun invoke(
            remoteDataSource: MovieRemoteDataSource,
            localDataSource: MovieLocalDataSource,
            dispatcher: CoroutineDispatcher = Dispatchers.IO
        ) = instance ?: synchronized(this) {
            instance ?: DefaultMovieRepository(remoteDataSource, localDataSource, dispatcher)
        }
    }

    override suspend fun getAllMovies(): Result<Movies> {
        return networkCall { remoteDataSource.getDiscover(MediaType.MOVIE) }
    }

    override suspend fun getAllTvshow(): Result<Movies> {
        return networkCall { remoteDataSource.getDiscover(MediaType.TV) }
    }

    override suspend fun getMovieById(id: Long): Result<Movie> {
        return networkCall { remoteDataSource.getMovieById(id, MediaType.MOVIE) }
    }

    override suspend fun getTvshowById(id: Long): Result<Movie> {
        return networkCall { remoteDataSource.getMovieById(id, MediaType.TV) }
    }

    override suspend fun getMovieFavorites(): Result<List<Movie>> {
        return localCall { localDataSource.getFavorites(MediaType.MOVIE) }
    }

    override suspend fun getTvshowFavorites(): Result<List<Movie>> {
        return localCall { localDataSource.getFavorites(MediaType.TV) }
    }

    override suspend fun addMovieToFavorite(movie: Movie): Result<Long> {
        return localCall { localDataSource.addToFavorite(movie) }
    }

    override suspend fun removeMovieFromFavorite(movieId: Long): Result<Int> {
        return localCall { localDataSource.removeFromFavorite(movieId) }
    }

    override suspend fun isInFavorite(id: Long): Boolean {
        return withContext(dispatcher) {
            try {
                localDataSource.isInFavorite(id) > 0
            } catch (e: Exception) {
                false
            }
        }
    }
}