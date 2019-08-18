package id.mustofa.app.amber.base

import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * @author Habib Mustofa
 * Indonesia on 17/08/19
 */
abstract class BaseRepository {

    protected suspend fun <T> networkCall(result: suspend () -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                with(result()) {
                    when {
                        isSuccessful -> Result.Success(body())
                        code() == 404 -> Result.Error(R.string.msg_not_found)
                        else -> Result.Error(R.string.msg_failed_fetch_data)
                    }
                }
            } catch (e: Exception) {
                Result.Error(R.string.msg_something_wrong)
            }
        }
    }
}