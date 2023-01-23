package app.gazdik_app.page1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.gazdik_app.net.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Page1ViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    init {
        getMovie()
    }

    private fun getMovie() {
        MovieApi.retrofitService.getMovies().enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body().toString()
                    println("\nGET sucessfull\n")
                    println(response.body().toString())
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    println("\nGET unsucessfull\n")
                }
            }
        )
    }
}
