package id.mustofa.app.academy.ui.reader.list

import android.view.ViewGroup
import id.mustofa.app.academy.R
import id.mustofa.app.academy.base.BaseRecyclerAdapter
import id.mustofa.app.academy.data.Module
import kotlinx.android.synthetic.main.items_module_list_custom.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
class ModuleListAdapter : BaseRecyclerAdapter<Module>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return object : ItemHolder(inflate(parent, R.layout.items_module_list_custom)) {

            override fun setItem(item: Module) {
                super.setItem(item)
                itemView.run {
                    with(item) {
                        textModuleTitle.text = title
                    }
                }
            }
        }
    }
}