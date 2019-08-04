package id.mustofa.app.academy.ui.reader

import id.mustofa.app.academy.data.Module

/**
 * @author Habib Mustofa
 * Indonesia on 04/08/19
 */
interface CourseReaderCallback {

    fun moveTo(module: Module)
}