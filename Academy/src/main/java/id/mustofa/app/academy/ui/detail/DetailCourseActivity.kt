package id.mustofa.app.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.ui.reader.CourseReaderActivity
import id.mustofa.app.academy.util.Const
import id.mustofa.app.academy.util.load
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailCourseViewModel
    private lateinit var adapter: DetailCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        getCourseExtra()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this)[DetailCourseViewModel::class.java]
    }

    private fun setupAdapter() {
        adapter = DetailCourseAdapter()
    }

    private fun setupRecyclerView() {
        rvModule.isNestedScrollingEnabled = true
        rvModule.layoutManager = LinearLayoutManager(this)
        rvModule.setHasFixedSize(true)
        rvModule.adapter = adapter
        val decoration = DividerItemDecoration(rvModule.context, DividerItemDecoration.VERTICAL)
        rvModule.addItemDecoration(decoration)
    }

    private fun getCourseExtra() {
        intent.extras?.run {
            viewModel.courseId = getString(Const.EXTRA_COURSE_DETAIL, null)
            adapter.loadData(viewModel.modules())
            populateCourse(viewModel.course())
        }
    }

    private fun populateCourse(course: Course) {
        course.run {
            textDetailTitle.text = title
            textDetailDescription.text = description
            textDetailDate.text = String.format("Deadline %s", deadline)
            imageDetailPoster.load(imagePath)
        }
        btnDetailStart.setOnClickListener { readCourse(viewModel.courseId) }
    }

    private fun readCourse(courseId: String) {
        startActivity(Intent(this, CourseReaderActivity::class.java)
            .apply { putExtra(Const.EXTRA_COURSE_READ, courseId) })
    }
}
