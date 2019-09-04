package id.mustofa.app.amber.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Habib Mustofa
 * Indonesia on 04/09/19
 */
// Helper
fun <B : ViewDataBinding> inflateBinding(parent: ViewGroup, @LayoutRes layout: Int): B {
    val inflater = LayoutInflater.from(parent.context)
    return DataBindingUtil.inflate(inflater, layout, parent, false)
}