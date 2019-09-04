package id.mustofa.app.amber.ui.discover

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemDiscoverBinding
import id.mustofa.app.amber.util.inflateBinding

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class DiscoverAdapter(private val itemCallback: (Movie) -> Unit) :
    ListAdapter<Movie, DiscoverAdapter.DiscoverView>(Movie.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverView {
        return DiscoverView(inflateBinding(parent, R.layout.item_discover)).apply {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { movie -> itemCallback(movie) }
            }
        }
    }

    override fun onBindViewHolder(holder: DiscoverView, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DiscoverView(
        private val binding: ItemDiscoverBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie?) {
            binding.item = movie
        }
    }
}