package id.mustofa.app.amber.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.mustofa.app.amber.ui.home.HomeActivity
import id.mustofa.app.amber.util.toActivity

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toActivity(HomeActivity::class)
        finish()
    }
}