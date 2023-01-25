package app.gazdik_app.net

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val base_url = "https://online-movie-database.p.rapidapi.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface MovieApiService {
    @GET("title/find")
    @Headers(
        "X-RapidAPI-Key: 3156fdf485msh59ed796406dbe6bp10ade1jsnb84d1e012606",
        "X-RapidAPI-Host: online-movie-database.p.rapidapi.com"
    )
    fun getMovies(@Query("q") movie_name: String): Call<String>
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
