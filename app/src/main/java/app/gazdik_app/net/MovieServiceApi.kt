package app.gazdik_app.net

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/*
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
*/

private const val base_url = "https://online-movie-database.p.rapidapi.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface MovieApiService {
//    @GET("title/find?q=predator")
    @GET("title/find")
    @Headers(
        "X-RapidAPI-Key: 3156fdf485msh59ed796406dbe6bp10ade1jsnb84d1e012606",
        "X-RapidAPI-Host: online-movie-database.p.rapidapi.com"
    )
//    fun getMovies(): Call<String>
    fun getMovies(@Query("q") movie_name: String): Call<String>
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}

/*
import com.squareup.moshi.Json
data class MovieData(
    val id: String,
    @Json(name = "l") val title: String,
    val qid: String,
    val rank: Number,
    @Json(name = "y") val year: Number,
    @Json(name = "s") val actors: String
)
*/