package app.gazdik_app.database

import app.gazdik_app.net.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRepo(private val db: MovieDatabase) {
    suspend fun saveMv(sendedMovie: MovieData){
        withContext(Dispatchers.IO){
            val movie = Movie(
                0,
                sendedMovie.id,
                sendedMovie.title,
                sendedMovie.titleType,
                sendedMovie.year?.toInt(),
                sendedMovie.runningTime?.toInt()
            )
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    db.movieDao.insertOne(movie)
                    println("Writing to DB should be successfull")
                }
            } catch (e: Exception) {
                println("Exception: \n" + e)
            }
        }
    }
}