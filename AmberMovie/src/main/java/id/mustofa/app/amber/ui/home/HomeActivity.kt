package id.mustofa.app.amber.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.mustofa.app.amber.R
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(homeToolbar)
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = HomePagerAdapter(applicationContext, supportFragmentManager)
        homeViewPager.adapter = pagerAdapter
        homeTab.setupWithViewPager(homeViewPager)
    }
}
