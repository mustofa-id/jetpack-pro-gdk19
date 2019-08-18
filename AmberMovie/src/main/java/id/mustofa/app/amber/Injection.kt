package id.mustofa.app.amber

import android.app.Application
import id.mustofa.app.amber.data.source.MovieRepository
import id.mustofa.app.amber.data.source.local.MovieLocalDataSource
import id.mustofa.app.amber.data.source.remote.ApiClient

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
object Injection {

    @Suppress("UNUSED_PARAMETER") // PLAN: `app` will used as ROOM database context
    fun provideRepository(app: Application): MovieRepository {
        return MovieRepository(
            MovieLocalDataSource(),
            ApiClient.movieRemoteDataSource
        )
    }
}