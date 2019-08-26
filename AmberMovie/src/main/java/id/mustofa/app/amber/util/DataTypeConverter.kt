package id.mustofa.app.amber.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import id.mustofa.app.amber.data.Genre
import java.util.*

/**
 * @author Habib Mustofa
 * Indonesia on 22/08/19
 */
class DataTypeConverter {

    private val gson: Gson by lazy { Gson() }

    @TypeConverter
    fun genresToString(genres: List<Genre>): String = gson.toJsonString(genres)

    @TypeConverter
    fun stringToGenres(genres: String): List<Genre> = gson.fromJsonString(genres)

    @TypeConverter
    fun mediaTypeToString(mediaType: MediaType) = mediaType.toString()

    @TypeConverter
    fun stringToMediaType(mediaType: String) =
        MediaType.valueOf(mediaType.toUpperCase(Locale.getDefault()))
}

