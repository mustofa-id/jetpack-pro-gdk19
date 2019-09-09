package id.mustofa.app.amber

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author Habib Mustofa
 * Indonesia on 06/08/19
 */
class SingleFragmentActivity : AppCompatActivity() {

    fun bindFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(android.R.id.content, fragment, tag)
            commit()
        }
    }
}