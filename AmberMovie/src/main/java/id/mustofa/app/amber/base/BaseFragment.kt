package id.mustofa.app.amber.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    protected fun <T> observe(liveData: LiveData<T>, block: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer(block))
    }
}