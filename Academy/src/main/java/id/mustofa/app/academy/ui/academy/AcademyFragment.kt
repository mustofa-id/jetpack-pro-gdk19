package id.mustofa.app.academy.ui.academy


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.ui.detail.DetailCourseActivity
import id.mustofa.app.academy.util.Const.EXTRA_COURSE_DETAIL
import id.mustofa.app.academy.util.generateCourses
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    private lateinit var adapter: AcademyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        adapter = AcademyAdapter()
        adapter.setItemClickListener { openCourseDetail(it) }
    }

    private fun setupRecyclerView() {
        rvAcademy.layoutManager = LinearLayoutManager(context)
        rvAcademy.setHasFixedSize(true)
        rvAcademy.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.loadData(generateCourses())
    }

    private fun openCourseDetail(course: Course) {
        startActivity(Intent(context, DetailCourseActivity::class.java)
            .apply { putExtra(EXTRA_COURSE_DETAIL, course.courseId) })
    }
}
