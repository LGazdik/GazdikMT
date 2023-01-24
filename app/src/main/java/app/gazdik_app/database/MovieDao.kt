package app.gazdik_app.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("select * from movie_table")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll( movie: List<Movie>)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("delete from movie_table")
    suspend fun deleteAll()

}

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    //abstract val movieDao: MovieDao

    private lateinit var INSTANCE: MovieDatabase

    fun getDatabase(context: Context): MovieDatabase {
        synchronized(MovieDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    MovieDatabase::class.java,
                    "movies").build()
            }
        }
        return INSTANCE
    }

}
