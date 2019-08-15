package id.mustofa.app.academy.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * @author Habib Mustofa
 * Indonesia on 15/08/19
 */
object LiveDataTestUtil {

    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOf<Any>(0)
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data[0] = t as Any
                latch.countDown()
                liveData.removeObserver(this)
            }
        }

        liveData.observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}