package id.mustofa.app.amber.data.source

import id.mustofa.app.amber.base.BaseRepository
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.data.Result
import id.mustofa.app.amber.data.source.local.MovieLocalDataSource
import id.mustofa.app.amber.data.source.remote.MovieRemoteDataSource
import id.mustofa.app.amber.data.source.remote.Movies
import id.mustofa.app.amber.util.MediaType

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 *
 * This class always construct as singleton instance
 */
class MovieRepository private constructor(
    @Suppress("unused") // local data source not used at the moment
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : BaseRepository(), MovieDataSource {

    companion object {

        @Volatile
        private var instance: MovieRepository? = null
        private val lock = Any()

        operator fun invoke(
            localDataSource: MovieLocalDataSource,
            remoteDataSource: MovieRemoteDataSource
        ) = instance ?: synchronized(lock) {
            instance ?: MovieRepository(localDataSource, remoteDataSource)
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