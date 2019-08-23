package id.mustofa.app.amber.ui.movie

import androidx.databinding.ViewDataBinding
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemMovieBinding

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class MovieAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_movie) {

    override fun getViewHolder(dataBinding: ViewDataBinding) =
        MovieViewHolder(dataBinding as ItemMovieBinding)

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : Holder(binding.root) {
        override fun setItem(item: Movie) {
            binding.movie = item
        }
    }
}