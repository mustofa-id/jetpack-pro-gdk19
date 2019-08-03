package id.mustofa.app.academy.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import id.mustofa.app.academy.R
import id.mustofa.app.academy.ui.academy.AcademyFragment
import id.mustofa.app.academy.ui.bookmark.BookmarkFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val selectedMenu = "selectedMenuBar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navHome.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> swipeFragment(AcademyFragment())
                R.id.action_bookmark -> swipeFragment(BookmarkFragment())
                else -> false
            }
        }
        navHome.selectedItemId = savedInstanceState?.getInt(selectedMenu) ?: R.id.action_home
    }

    private fun swipeFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment)
            .commit()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(selectedMenu, navHome.selectedItemId)
    }
}
