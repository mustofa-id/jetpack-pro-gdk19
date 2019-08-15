package id.mustofa.app.academy.ui.academy


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.ui.detail.DetailCourseActivity
import id.mustofa.app.academy.util.Const.EXTRA_COURSE_DETAIL
import id.mustofa.app.academy.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    private lateinit var viewModel: AcademyViewModel
    private lateinit var adapter: AcademyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        subscribeObserve()
    }

    private fun setupViewModel() {
        viewModel = activity!!.obtainViewModel(AcademyViewModel::class)
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

    private fun subscribeObserve() {
        pbAcademy.visibility = View.VISIBLE
        viewModel.courses().observe(this, Observer {
            adapter.loadData(it)
            pbAcademy.visibility = View.GONE
        })
    }

    private fun openCourseDetail(course: Course) {
        startActivity(Intent(context, DetailCourseActivity::class.java)
            .apply { putExtra(EXTRA_COURSE_DETAIL, course.courseId) })
    }
}
