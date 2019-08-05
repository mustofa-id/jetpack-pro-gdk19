package id.mustofa.app.amber.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
abstract class BaseRecyclerAdapter<T : BaseModel> :
    RecyclerView.Adapter<BaseRecyclerAdapter<T>.Holder>() {

    private var data = listOf<T>()
    private var itemClickListener: ((T) -> Unit)? = null

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position]._id

    override fun setHasStableIds(hasStableIds: Boolean) = super.setHasStableIds(true)

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.setItem(data[position])

    fun setOnItemClickListener(listener: (T) -> Unit) {
        this.itemClickListener = listener
    }

    fun populateData(data: List<T>, notify: Boolean = true) {
        this.data = data
        if (notify) notifyDataSetChanged()
    }

    open fun inflate(parent: ViewGroup, layoutName: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutName, parent, false)
    }

    abstract inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        open fun setItem(item: T) {
            itemClickListener?.let { itemView.setOnClickListener { it(item) } }
        }
    }
}