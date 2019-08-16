package id.mustofa.app.academy.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * @author Habib Mustofa
 * Indonesia on 16/08/19
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()

    fun decrement() = idlingResource.decrement()
}