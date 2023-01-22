package app.gazdik_app.Page1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.gazdik_app.net.GeoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Page1ViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private fun getGeoCity() {
        GeoApi.retrofitService.getCity().enqueue(
            object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "fail: " + t.message
                }
            })

    }


}