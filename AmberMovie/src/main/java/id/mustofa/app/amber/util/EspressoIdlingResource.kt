package id.mustofa.app.amber.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * @author Habib Mustofa
 * Indonesia on 20/08/19
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()

    fun decrement() {
        if (!idlingResource.isIdleNow) {
            idlingResource.decrement()
        }
    }
}

inline fun <T> wrapWithEspressoIdlingResource(block: () -> T): T {
    EspressoIdlingResource.increment()
    return try {
        block()
    } finally {
        EspressoIdlingResource.decrement()
    }
}