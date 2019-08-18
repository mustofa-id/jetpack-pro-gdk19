package id.mustofa.app.amber.data.source.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
object ApiClient {

    private const val baseUrl = "https://api.themoviedb.org/3/"

    val movieRemoteDataSource: MovieRemoteDataSource by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        return@lazy retrofit.create(MovieRemoteDataSource::class.java)
    }
}