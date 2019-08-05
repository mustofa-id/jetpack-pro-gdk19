package id.mustofa.app.amber.ui.home


import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.mustofa.app.amber.R
import id.mustofa.app.amber.ui.movie.MovieFragment
import id.mustofa.app.amber.ui.tvshow.TvshowFragment

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class HomePagerAdapter(private val context: Context, manager: FragmentManager) :
    FragmentPagerAdapter(manager) {

    private val fragments = linkedMapOf(
        R.string.title_tab_movie to MovieFragment(),
        R.string.title_tab_tvshow to TvshowFragment()
    )

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(fragments.keys.toList()[position])
    }

    override fun getItem(position: Int) = fragments.values.toList()[position]

    override fun getCount() = fragments.size
}