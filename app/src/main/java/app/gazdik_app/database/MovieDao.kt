package app.gazdik_app.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query(/* value = */ "select * from movie_table")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOne(movie: Movie)

    @Delete
    fun delete(movie: Movie): Void

    @Query(/* value = */ "DELETE FROM movie_table")
    fun nukeTable(): Int
}
