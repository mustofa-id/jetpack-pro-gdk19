package id.mustofa.app.academy.util

import android.app.Application
import id.mustofa.app.academy.data.source.AcademyRepository
import id.mustofa.app.academy.data.source.local.AcademyLocalDataSource
import id.mustofa.app.academy.data.source.remote.AcademyRemoteDataSource
import id.mustofa.app.academy.data.source.remote.JsonHelper

/**
 * @author Habib Mustofa
 * Indonesia on 13/08/19
 */
object Injection {

    fun provideRepository(app: Application): AcademyRepository {
        return AcademyRepository.instance(
            AcademyLocalDataSource(),
            AcademyRemoteDataSource.instance(JsonHelper(app))
        )
    }
}
