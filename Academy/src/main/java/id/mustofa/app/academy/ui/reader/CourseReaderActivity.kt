package id.mustofa.app.academy.ui.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Module
import id.mustofa.app.academy.ui.reader.content.ModuleContentFragment
import id.mustofa.app.academy.ui.reader.list.ModuleListFragment
import id.mustofa.app.academy.util.Const

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        viewModel = ViewModelProviders.of(this)[CourseReaderViewModel::class.java]

        intent.extras?.run {
            viewModel.courseId = getString(Const.EXTRA_COURSE_READ)!!
            populateFragment()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) finish()
        else super.onBackPressed()
    }

    override fun moveTo(module: Module) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, ModuleContentFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun populateFragment() {
        supportFragmentManager.run {
            val fragment = findFragmentByTag(Const.TAG_MODULE_LIST_FRAGMENT)
            if (fragment == null) {
                beginTransaction()
                    .add(R.id.frame_container, ModuleListFragment(), Const.TAG_MODULE_LIST_FRAGMENT)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
