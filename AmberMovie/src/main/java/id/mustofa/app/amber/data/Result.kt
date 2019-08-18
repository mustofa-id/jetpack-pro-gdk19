package id.mustofa.app.amber.data

/**
 * @author Habib Mustofa
 * Indonesia on 18/08/19
 */
sealed class Result<out T> {

    class Success<out T>(val data: T?) : Result<T>()
    class Error(val message: Int) : Result<Nothing>()
}