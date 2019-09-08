package id.mustofa.app.amber.ui.home

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import id.mustofa.app.amber.R
import id.mustofa.app.amber.ui.discover.movie.MovieFragment
import id.mustofa.app.amber.ui.discover.tvshow.TvshowFragment
import id.mustofa.app.amber.ui.favorite.movie.MovieFavoriteFragment
import id.mustofa.app.amber.ui.favorite.tvshow.TvshowFavoriteFragment
import id.mustofa.app.amber.util.snackIt
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class HomeActivity : AppCompatActivity() {

    private var closeable = false
    private lateinit var pagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(homeToolbar)
        setupViewPager()
        setupBottomNav()
        showDiscover()
    }

    private fun setupViewPager() {
        pagerAdapter = HomePagerAdapter(applicationContext, supportFragmentManager)
        homeViewPager.adapter = pagerAdapter
        homeTab.setupWithViewPager(homeViewPager)
    }

    private fun setupBottomNav() {
        homeNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_discover -> showDiscover()
                R.id.nav_favorite -> showFavorite()
                else -> showAbout()
            }
        }
    }

    override fun onBackPressed() {
        if (closeable) super.onBackPressed()
        Handler().postDelayed({ closeable = false }, 2000)
        homeNavContainer.snackIt(R.string.msg_confirm_close)
        closeable = true
    }

    private fun showDiscover(): Boolean {
        homeToolbar.setTitle(R.string.title_nav_discover)
        pagerAdapter.setFragments(
            linkedMapOf(
                R.string.title_tab_movie to MovieFragment(),
                R.string.title_tab_tvshow to TvshowFragment()
            )
        )
        return true
    }

    private fun showFavorite(): Boolean {
        homeToolbar.setTitle(R.string.title_nav_favorite)
        pagerAdapter.setFragments(
            linkedMapOf(
                R.string.title_tab_movie to MovieFavoriteFragment(),
                R.string.title_tab_tvshow to TvshowFavoriteFragment()
            )
        )
        return true
    }

    private fun showAbout(): Boolean {
        homeNavContainer.snackIt(R.string.desc_about, Snackbar.LENGTH_INDEFINITE) {
            setAction(android.R.string.ok) { dismiss() }
        }
        return false
    }
}
