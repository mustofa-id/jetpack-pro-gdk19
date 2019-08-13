package id.mustofa.app.academy.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Content
import id.mustofa.app.academy.ui.reader.CourseReaderActivity
import id.mustofa.app.academy.ui.reader.CourseReaderViewModel
import id.mustofa.app.academy.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as CourseReaderActivity).obtainViewModel(CourseReaderViewModel::class)
        viewModel.module().run { content?.let { populateWebView(it) } }
    }

    private fun populateWebView(content: Content) {
        webView.loadData(content.content, "text/html", "UTF-8")
    }
}
