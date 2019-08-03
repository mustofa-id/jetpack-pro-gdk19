package id.mustofa.app

import androidx.lifecycle.ViewModel

/**
 * @author Habib Mustofa
 * Indonesia on 29/07/19.
 */
class MainViewModel : ViewModel() {

    var result: Double = 0.0

    fun squareArea(length: String, width: String, height: String) {
        result = length.toDouble() * width.toDouble() * height.toDouble()
    }
}