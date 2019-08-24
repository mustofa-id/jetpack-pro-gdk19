package id.mustofa.app.amber.util

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import id.mustofa.app.amber.base.BaseModel
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
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
@BindingAdapter("app:items")
fun <T : BaseModel> RecyclerView.setItems(items: List<T>?) {
    val itemAdapter = adapter as SimpleRecyclerAdapter<T>
    items?.let { itemAdapter.populateData(it) }
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