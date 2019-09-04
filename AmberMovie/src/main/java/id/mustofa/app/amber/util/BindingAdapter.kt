package id.mustofa.app.amber.util

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import id.mustofa.app.amber.data.Genre

/**
 * @author Habib Mustofa
 * Indonesia on 23/08/19
 */
@BindingAdapter("app:shown")
fun shown(view: View, shown: Boolean) {
    view.visibility = if (shown) View.VISIBLE else View.GONE
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:list")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.setList(list: List<T>?) {
    val listAdapter = adapter as ListAdapter<T, VH>
    list?.let { listAdapter.submitList(it) }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:list")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.setList(list: PagedList<T>?) {
    val listAdapter = adapter as PagedListAdapter<T, VH>
    list?.let { listAdapter.submitList(it) }
}

@BindingAdapter("app:srcSmall")
fun ImageView.load185(path: String?) {
    path?.let { loadTmdbImage(it) }
}

@BindingAdapter("app:srcLive")
fun ImageButton.srcLive(resId: Int?) {
    resId?.let { setImageResource(it) }
}

@BindingAdapter("app:childrenGenres")
fun ChipGroup.childrenGenres(genres: List<Genre>?) {
    genres?.forEach {
        addView(Chip(context).apply {
            text = it.name
            isClickable = false
            setTextColor(Color.DKGRAY)
        })
    }
}