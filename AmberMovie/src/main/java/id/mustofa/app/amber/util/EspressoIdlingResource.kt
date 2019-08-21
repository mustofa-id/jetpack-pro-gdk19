package id.mustofa.app.amber.util

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Habib Mustofa
 * Indonesia on 20/08/19
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val idlingResource = SimpleIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()

    fun decrement() {
        if (!idlingResource.isIdleNow) {
            idlingResource.decrement()
        }
    }
}

/*
 * Fix test fail when using default CountingIdlingResource. Caused by:
 * `Method isEmpty in android.text.TextUtils not mocked.`
 * CountingIdlingResource using TextUtils#isEmpty() for check resourceName
 *
 * Code references:
 * https://github.com/googlesamples/android-testing/blob/master/ui/espresso/IdlingResourceSample/app/src/main/java/com/example/android/testing/espresso/IdlingResourceSample/IdlingResource/SimpleIdlingResource.java
 * https://github.com/googlesamples/android-architecture/blob/master/app/src/main/java/com/example/android/architecture/blueprints/todoapp/util/SimpleCountingIdlingResource.kt
 */
class SimpleIdlingResource(private val resourceName: String) : IdlingResource {

    // private val isIdle = AtomicBoolean(true)
    private val counter = AtomicInteger(0)

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName() = resourceName

    override fun isIdleNow() = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    // fun setIdleState(state: Boolean) {
    //    isIdle.set(state)
    //    if (isIdleNow) {
    //        // notify espresso we are idle now
    //        callback?.onTransitionToIdle()
    //    }
    // }

    fun increment() = counter.getAndIncrement()

    fun decrement() {
        val counterValue = counter.decrementAndGet()
        if (counterValue == 0)
        // notify espresso we are idle now
            callback?.onTransitionToIdle()
        else if (counterValue < 0)
            throw IllegalStateException("Counter corrupted!")
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