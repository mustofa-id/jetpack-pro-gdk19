package id.mustofa.app.academy.ui.reader.list


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.mustofa.app.academy.R
import id.mustofa.app.academy.ui.reader.CourseReaderCallback
import id.mustofa.app.academy.ui.reader.CourseReaderViewModel
import id.mustofa.app.academy.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment() {

    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        subscribeObserve()
    }

    private fun setupViewModel() {
        viewModel = activity!!.obtainViewModel(CourseReaderViewModel::class)
    }

    private fun setupAdapter() {
        adapter = ModuleListAdapter()
        adapter.setItemClickListener {
            courseReaderCallback.moveTo(it)
            viewModel.moduleId = it.moduleId
        }
    }

    private fun setupRecyclerView() {
        rvModuleList.layoutManager = LinearLayoutManager(context)
        rvModuleList.setHasFixedSize(true)
        rvModuleList.adapter = adapter
        val decoration = DividerItemDecoration(rvModuleList.context, DividerItemDecoration.VERTICAL)
        rvModuleList.addItemDecoration(decoration)
    }

    private fun subscribeObserve() {
        pbModuleList.visibility = View.VISIBLE
        viewModel.modules().observe(this, Observer {
            adapter.loadData(it)
            pbModuleList.visibility = View.GONE
        })
    }
}
