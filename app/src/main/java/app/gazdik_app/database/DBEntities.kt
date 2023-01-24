package app.gazdik_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.gazdik_app.net.MovieData

/*
 * DatabaseVideo represents a video entity in the database.
*/

@Entity(tableName = "movie_table")
data class Movie constructor(
    @PrimaryKey
    val movieID: String,
    val id: String?,
    val title: String?,
    val titleType: String?,
    val year: Int?,
    val runningTime: Int?)


fun List<Movie>.asDomainModel(): List<MovieData> {
    return map {
        MovieData(
            id = it.id,
            title = it.title,
            titleType = it.titleType,
            year = it.year,
            runningTime = it.runningTime)
    }
}
