package id.mustofa.app.amber.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.mustofa.app.amber.data.Movie
import id.mustofa.app.amber.util.DataTypeConverter

/**
 * @author Habib Mustofa
 * Indonesia on 21/08/19
 */
@TypeConverters(DataTypeConverter::class)
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private const val databaseName = "database.db"

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
        }
    }

    abstract fun movieLocalDataSource(): MovieLocalDataSource
}