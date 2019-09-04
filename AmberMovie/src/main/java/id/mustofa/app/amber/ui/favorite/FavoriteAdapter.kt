package id.mustofa.app.amber.ui.favorite

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemFavoriteBinding
import id.mustofa.app.amber.util.inflateBinding

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class FavoriteAdapter(private val itemCallback: (Movie) -> Unit) :
    PagedListAdapter<Movie, FavoriteAdapter.MovieView>(Movie.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieView {
        return MovieView(inflateBinding(parent, R.layout.item_favorite)).apply {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { movie -> itemCallback(movie) }
            }
        }
    }

    override fun onBindViewHolder(holder: MovieView, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieView(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie?) {
            binding.item = movie
        }
    }
}