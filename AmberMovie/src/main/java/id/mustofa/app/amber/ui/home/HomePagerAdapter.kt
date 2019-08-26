package id.mustofa.app.amber.ui.home


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class HomePagerAdapter(private val context: Context, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager) {

    private var fragments = linkedMapOf<Int, Fragment>()
    private var shouldUpdate = false

    fun setFragments(fragments: LinkedHashMap<Int, Fragment>) {
        this.fragments = fragments
        shouldUpdate = true
        notifyDataSetChanged()
        shouldUpdate = false
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(fragments.keys.toList()[position])
    }

    override fun getItem(position: Int) = fragments.values.toList()[position]

    override fun getCount() = fragments.size

    override fun getItemPosition(obj: Any): Int {
        return if (shouldUpdate) POSITION_NONE else super.getItemPosition(obj)
    }
}