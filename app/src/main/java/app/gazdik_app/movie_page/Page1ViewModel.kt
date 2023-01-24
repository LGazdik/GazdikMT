package app.gazdik_app.movie_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.gazdik_app.net.MovieApi
import app.gazdik_app.net.MovieData
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Page1ViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

//    private val _movieData = List<MovieData>()
//    val movieData: List<MovieData> get() = _movieData

    private var dat: MutableList<MovieData> = mutableListOf()
    val gson = Gson()

    private fun ListFill(j: JsonObject) {
//        val gson = Gson()
        val results = j.get("results").asJsonArray
        println("res size: " + results.size())
        for (i in 0 until results.size()) {
            var d: MovieData = gson.fromJson(results[i], MovieData::class.java)
            dat.add(d)

            println(dat[i])
        }
    }

    init {
        getMovie()
    }

    private fun getMovie() {
        MovieApi.retrofitService.getMovies("predator").enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = "GET sucessfull:"
                    println(_response.value)
                    var respJson = gson.fromJson(response.body(), JsonObject::class.java)
                    ListFill(respJson)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "GET unsucessfull: " + t.message
                    println(_response.value)
                }
            }
        )
    }
}
