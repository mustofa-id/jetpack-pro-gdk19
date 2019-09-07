package id.mustofa.app.amber

import android.database.Cursor
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.paging.LimitOffsetDataSource
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

// default pagedList config
fun getPagedConfig(size: Int) = PagedList.Config
    .Builder()
    .setEnablePlaceholders(false)
    .setPageSize(size)
    .setMaxSize(size + 2)
    .setPrefetchDistance(1)
    .build()

fun <T> mockDataSourceFactory(itemList: List<T>) = object :
    DataSource.Factory<Int, T>() {

    override fun create(): DataSource<Int, T> {
        val mockDb = mock(RoomDatabase::class.java)
        val mockQuery = mock(RoomSQLiteQuery::class.java)

        `when`(mockDb.invalidationTracker).thenReturn(mock(InvalidationTracker::class.java))
        `when`(mockQuery.sql).thenReturn("")
        return MockLimitDataSource(itemList, mockDb, mockQuery)
    }
}

private class MockLimitDataSource<T>(
    private val list: List<T>,
    db: RoomDatabase,
    query: RoomSQLiteQuery
) : LimitOffsetDataSource<T>(db, query, false, null) {

    override fun convertRows(cursor: Cursor?) = list.toMutableList()

    override fun countItems(): Int = list.count()

    override fun isInvalid(): Boolean = false

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {}

    override fun loadRange(startPosition: Int, loadCount: Int) =
        list.subList(startPosition, startPosition + loadCount).toMutableList()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        callback.onResult(list, 0)
    }
}