package id.mustofa.app.amber.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
abstract class SimpleRecyclerAdapter<T : BaseModel>(@LayoutRes private val layout: Int) :
    RecyclerView.Adapter<SimpleRecyclerAdapter<T>.Holder>() {

    private var data = listOf<T>()
    private var itemClickListener: ((T) -> Unit)? = null

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position].id

    override fun setHasStableIds(hasStableIds: Boolean) = super.setHasStableIds(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val holder = getViewHolder(view)
        // only set view click listener if itemClickListener not null
        itemClickListener?.let {
            holder.itemView.setOnClickListener { it(data[holder.adapterPosition]) }
        }
        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.setItem(data[position])

    abstract fun getViewHolder(view: View): Holder

    abstract inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setItem(item: T)
    }

    fun populateData(data: List<T>, notify: Boolean = true) {
        this.data = data
        if (notify) notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (T) -> Unit) {
        this.itemClickListener = listener
    }
}