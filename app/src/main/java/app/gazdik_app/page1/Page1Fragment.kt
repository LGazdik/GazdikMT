package app.gazdik_app.page1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.gazdik_app.R

class Page1Fragment : Fragment() {

    private val viewModel: Page1ViewModel by lazy {
        ViewModelProvider(this).get(Page1ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("VM resp\n")
        println(viewModel.response)

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}