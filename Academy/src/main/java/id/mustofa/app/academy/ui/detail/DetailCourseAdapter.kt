package id.mustofa.app.academy.ui.detail

import android.view.ViewGroup
import id.mustofa.app.academy.R
import id.mustofa.app.academy.base.BaseRecyclerAdapter
import id.mustofa.app.academy.data.Module
import kotlinx.android.synthetic.main.items_module_list.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
class DetailCourseAdapter : BaseRecyclerAdapter<Module>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return object : ItemHolder(inflate(parent, R.layout.items_module_list)) {

            override fun setItem(item: Module) {
                super.setItem(item)
                itemView.run {
                    textTitle.text = item.title
                }
            }
        }
    }
}