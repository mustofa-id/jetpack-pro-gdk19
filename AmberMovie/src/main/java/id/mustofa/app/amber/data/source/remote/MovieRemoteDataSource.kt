package id.mustofa.app.amber.data.source.remote

import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.Const
import id.mustofa.app.amber.util.MediaType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
interface MovieRemoteDataSource {

    @GET("discover/{type}?language=en-US&${Const.TMDB_API_KEY}")
    suspend fun getDiscover(@Path("type") type: MediaType): Response<Movies>

    @GET("{type}/{id}?language=en-US&${Const.TMDB_API_KEY}")
    suspend fun getMovieById(
        @Path("id") id: Long,
        @Path("type") type: MediaType
    ): Response<Movie>
}