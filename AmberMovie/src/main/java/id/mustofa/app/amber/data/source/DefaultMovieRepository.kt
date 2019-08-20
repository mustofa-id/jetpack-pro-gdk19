package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.base.BaseRepository
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.remote.MovieRemoteDataSource
import id.mustofa.app.amber.data.source.remote.Movies
import id.mustofa.app.amber.util.MediaType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 *
 * This class always construct as singleton instance
 */
class DefaultMovieRepository private constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    override val dispatcher: CoroutineDispatcher
) : MovieRepository, BaseRepository() {

    companion object {

        @Volatile
        private var instance: DefaultMovieRepository? = null

        operator fun invoke(
            remoteDataSource: MovieRemoteDataSource,
            dispatcher: CoroutineDispatcher = Dispatchers.IO
        ) = instance ?: synchronized(this) {
            instance ?: DefaultMovieRepository(remoteDataSource, dispatcher)
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
}