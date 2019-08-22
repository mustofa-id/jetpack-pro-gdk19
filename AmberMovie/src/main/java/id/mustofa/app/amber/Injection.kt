package id.mustofa.app.amber

import android.app.Application
import id.mustofa.app.amber.data.source.DefaultMovieRepository
import id.mustofa.app.amber.data.source.local.AppDatabase
import id.mustofa.app.amber.data.source.remote.ApiClient

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
object Injection {

    fun provideRepository(app: Application): DefaultMovieRepository {
        return DefaultMovieRepository(
            ApiClient.movieRemoteDataSource,
            AppDatabase(app.applicationContext).movieLocalDataSource()
        )
    }
}