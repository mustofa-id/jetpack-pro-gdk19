package id.mustofa.app.amber.ui.discover

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.mustofa.app.amber.R
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.inflate
import id.mustofa.app.amber.util.loadTmdbImage
import kotlinx.android.synthetic.main.item_discover.view.*

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class DiscoverAdapter(private val itemCallback: (Movie) -> Unit) :
    ListAdapter<Movie, DiscoverAdapter.DiscoverView>(Movie.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverView {
        return DiscoverView(parent.inflate(R.layout.item_discover)).apply {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { movie -> itemCallback(movie) }
            }
        }
    }

    override fun onBindViewHolder(holder: DiscoverView, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DiscoverView(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie?) {
            with(itemView) {
                movie?.run {
                    textItemMovieTitle.text = title
                    textItemMovieRating.text = String.format("%s", voteAverage / 2)
                    imgItemMoviePoster.loadTmdbImage(posterPath)
                }
            }
        }
    }
}