package id.mustofa.app.amber.data.source.remote

import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * @author Habib Mustofa
 * Indonesia on 19/08/19
 */
class FakeMovieRemoteDataSource(
    private val movies: List<Movie>,
    private val tvshows: List<Movie>
) : MovieRemoteDataSource {

    override suspend fun getDiscover(type: MediaType): Response<Movies> {
        return when (type) {
            MediaType.MOVIE -> Response.success(Movies(movies))
            else -> Response.success(Movies(tvshows))
        }
    }

    override suspend fun getMovieById(id: Long, type: MediaType): Response<Movie> {
        (if (type == MediaType.MOVIE) movies else tvshows)
            .firstOrNull { it.id == id }?.let { return Response.success(it) }
        return Response.error(404, ResponseBody.create(null, ""))
    }
}