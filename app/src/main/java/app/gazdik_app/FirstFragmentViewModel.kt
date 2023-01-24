package app.gazdik_app

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

class FirstFragmentViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    private var _movDat: MutableList<MovieData> = mutableListOf()
    val movDat: List<MovieData> get() = _movDat

    val gson = Gson()

    private fun ListFill(j: JsonObject) {
        val results = j.get("results").asJsonArray
        println("res size: " + results.size())
        for (i in 0 until results.size()) {
            var d: MovieData = gson.fromJson(results[i], MovieData::class.java)
            _movDat.add(d)
            println(_movDat[i])
        }
    }

    fun getMovie( s:String ) {
        MovieApi.retrofitService.getMovies(s).enqueue(
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