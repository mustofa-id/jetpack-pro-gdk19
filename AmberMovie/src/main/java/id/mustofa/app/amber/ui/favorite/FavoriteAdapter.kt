package id.mustofa.app.amber.ui.favorite

import androidx.databinding.ViewDataBinding
import id.mustofa.app.amber.R
import id.mustofa.app.amber.base.SimpleRecyclerAdapter
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.databinding.ItemFavoriteBinding

/**
 * @author Habib Mustofa
 * Indonesia on 26/08/19
 */
class FavoriteAdapter : SimpleRecyclerAdapter<Movie>(R.layout.item_favorite) {

    override fun getViewHolder(dataBinding: ViewDataBinding) =
        FavoriteViewHolder(dataBinding as ItemFavoriteBinding)

    inner class FavoriteViewHolder(
        private val binding: ItemFavoriteBinding
    ) : Holder(binding.root) {
        override fun setItem(item: Movie) {
            binding.item = item
        }
    }
}