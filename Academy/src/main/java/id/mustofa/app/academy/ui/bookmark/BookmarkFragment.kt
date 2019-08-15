package id.mustofa.app.academy.ui.bookmark


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Course
import id.mustofa.app.academy.ui.detail.DetailCourseActivity
import id.mustofa.app.academy.util.Const
import id.mustofa.app.academy.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment() {

    private lateinit var viewModel: BookmarkViewModel
    private lateinit var adapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        viewModel = activity!!.obtainViewModel(BookmarkViewModel::class)
    }

    private fun setupAdapter() {
        adapter = BookmarkAdapter()
        adapter.setItemClickListener { openCourseDetail(it) }
        adapter.setShareClickListener { shareCourse(it) }
    }

    private fun setupRecyclerView() {
        rvBookmark.layoutManager = LinearLayoutManager(context)
        rvBookmark.setHasFixedSize(true)
        rvBookmark.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.loadData(viewModel.bookmarks())
    }

    private fun openCourseDetail(course: Course) {
        startActivity(Intent(context, DetailCourseActivity::class.java)
            .apply { putExtra(Const.EXTRA_COURSE_DETAIL, course.courseId) })
    }

    private fun shareCourse(course: Course) {
        ShareCompat.IntentBuilder.from(activity)
            .setType("text/plain")
            .setChooserTitle(R.string.share_title)
            .setText("Segera daftar kelas ${course.title} di dicoding.com")
            .startChooser()
    }
}
