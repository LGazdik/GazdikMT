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
import app.gazdik_app.database.getDatabase
import app.gazdik_app.databinding.FragmentSavedBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        sh()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonDeleteAll.setOnClickListener {
            deleteMovieData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sh() {
        movieDB = getDatabase(requireContext())
        GlobalScope.launch {
            var listerin = movieDB.movieDao.getAll()
            for (i in 0 until listerin.size) {
                println("movie in DB: " + listerin[i].id + " " + listerin[i].title)
            }
        }
    }

    private fun deleteMovieData() {
        movieDB = getDatabase(requireContext())
        GlobalScope.launch {
            try {
                movieDB.movieDao.nukeTable()

                var listerin = movieDB.movieDao.getAll()
                if (listerin.size == 0) {
                    println("DB successfully nuked")
                }
                for (i in 0 until listerin.size) {
                    println("movie in DB: " + listerin[i].id + " " + listerin[i].title)
                }

            } catch (e: Exception) {
                println("agony: " + e)
            }

        }
    }
}