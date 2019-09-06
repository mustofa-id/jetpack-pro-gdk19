package id.mustofa.app.amber.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import id.mustofa.app.amber.data.Movie

/**
 * @author Habib Mustofa
 * Indonesia on 04/09/19
 */
// Helper
fun <B : ViewDataBinding> inflateBinding(parent: ViewGroup, @LayoutRes layout: Int): B {
    val inflater = LayoutInflater.from(parent.context)
    return DataBindingUtil.inflate(inflater, layout, parent, false)
}

class SingleBoundaryCallback<T>(
    private val callback: (T?) -> Unit
) : PagedList.BoundaryCallback<T>() {

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        super.onItemAtEndLoaded(itemAtEnd)
        callback(itemAtEnd)
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        callback(null)
    }

    override fun onItemAtFrontLoaded(itemAtFront: T) {
        super.onItemAtFrontLoaded(itemAtFront)
        callback(itemAtFront)
    }
}