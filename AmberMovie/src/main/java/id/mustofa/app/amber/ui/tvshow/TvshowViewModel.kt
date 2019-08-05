package id.mustofa.app.amber.ui.tvshow

import androidx.lifecycle.ViewModel
import id.mustofa.app.amber.data.source.local.LocalRepository

/**
 * @author Habib Mustofa
 * Indonesia on 05/08/19
 */
class TvshowViewModel : ViewModel() {

    private val repository = LocalRepository()

    fun getTvshows() = repository.tvshows()
}