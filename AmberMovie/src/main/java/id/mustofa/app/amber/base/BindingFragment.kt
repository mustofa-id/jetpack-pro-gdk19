package id.mustofa.app.amber.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
abstract class BindingFragment<BindingType : ViewDataBinding?>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    protected var binding: BindingType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<BindingType>(inflater, layoutId, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }
}