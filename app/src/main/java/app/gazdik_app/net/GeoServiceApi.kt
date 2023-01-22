package app.gazdik_app.net

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val base_url = "https://wft-geo-db.p.rapidapi.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(base_url)
    .build()

interface GeoApiService {
    @GET("city")
    fun getCity():
            Call<String>
}
object GeoApi {
    val retrofitService : GeoApiService by lazy {
        retrofit.create(GeoApiService::class.java) }
}

/*
val client = OkHttpClient()
val request = Request.Builder()
    .url(base_url+"/v1/geo/cities")
    .get()
    .addHeader("X-RapidAPI-Key", "3156fdf485msh59ed796406dbe6bp10ade1jsnb84d1e012606")
    .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
    .build()

val response = client.newCall(request).execute()
*/