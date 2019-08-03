package id.mustofa.app.academy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Habib Mustofa
 * Indonesia on 03/08/19
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseRecyclerAdapter<T>.ItemHolder>() {

    private var data = listOf<T>()
    private var itemClickListener: ((T) -> Unit)? = null

    fun loadData(data: List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (T) -> Unit) {
        this.itemClickListener = listener
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holderItem: ItemHolder, position: Int) {
        holderItem.setItem(data[position])
    }

    protected fun inflate(parent: ViewGroup, layout: Int): View {
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }

    abstract inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun setItem(item: T) {
            itemClickListener?.let { itemView.setOnClickListener { it(item) } }
        }
    }
}