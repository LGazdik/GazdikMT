package app.gazdik_app.saved_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.gazdik_app.R
import app.gazdik_app.database.MovieDatabase
import app.gazdik_app.databinding.FragmentSavedBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 * */
class SavedMoviesFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieDB: MovieDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_saved,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonDeleteAll.setOnClickListener{
            deleteMovieData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun deleteMovieData(){
        movieDB.movieDao.nukeTable()
    }
}