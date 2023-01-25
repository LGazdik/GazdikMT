package app.gazdik_app.saved_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedMoviesViewModel: ViewModel() {

    val tvdb: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}