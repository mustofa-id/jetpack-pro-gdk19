package id.mustofa.app.amber.ui.home

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import id.mustofa.app.amber.R
import id.mustofa.app.amber.util.snackIt
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class HomeActivity : AppCompatActivity() {

    private var isCloseable = false

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

    override fun onBackPressed() {
        if (isCloseable) super.onBackPressed()
        Handler().postDelayed({ isCloseable = false }, 2000)
        snackIt(getString(R.string.msg_confirm_close))
        isCloseable = true
    }
}
