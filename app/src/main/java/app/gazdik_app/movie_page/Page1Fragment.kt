package app.gazdik_app.movie_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.gazdik_app.R
import app.gazdik_app.databinding.FragmentMovieBinding
import app.gazdik_app.first_page.FirstFragmentViewModel

class Page1Fragment : Fragment() {

//    private val FFviewModel: Page1ViewModel by lazy {
//        ViewModelProvider(this).get(Page1ViewModel::class.java)
//    }

    private val viewModel: FirstFragmentViewModel by activityViewModels()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie,
            container,
            false
        )

        //binding.setViewModel(viewModel)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataFill()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataFill() {
        if (viewModel.movDat.isEmpty()) {
            println("empty array !!!\n")
            return
        }
        var da = viewModel.movDat[0]
        binding.movieTitleText.text = "Movie title: " + da.title
        binding.movieTitleTypeText.text = "Movie type: " + da.titleType
        binding.movieYearText.text = "Year: " + da.year.toString()
        binding.movieRuntimeText.text = "Running time (min): " + da.runningTime.toString()
    }

}