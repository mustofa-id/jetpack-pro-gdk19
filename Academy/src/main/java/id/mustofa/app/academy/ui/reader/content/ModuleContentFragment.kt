package id.mustofa.app.academy.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import id.mustofa.app.academy.R
import id.mustofa.app.academy.data.Content
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
        viewModel = activity!!.obtainViewModel(CourseReaderViewModel::class)

        pbModuleContent.visibility = View.VISIBLE
        viewModel.module().observe(this, Observer {
            it?.content?.let { content -> populateWebView(content) }
            pbModuleContent.visibility = View.GONE
        })
    }

    private fun populateWebView(content: Content) {
        webView.loadData(content.content, "text/html", "UTF-8")
    }
}
