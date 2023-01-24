package app.gazdik_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.gazdik_app.net.MovieData

@Entity(tableName = "movie_table")
data class Movie constructor(
    @PrimaryKey(autoGenerate = true)
    val movieID: Int = 0,
    val id: String?,
    val title: String?,
    val titleType: String?,
    val year: Int?,
    val runningTime: Int?)

fun Movie.toMovieData(): MovieData {
    return MovieData(
        id = id,
        title = title,
        titleType = titleType,
        year = year,
        runningTime = runningTime)
}
