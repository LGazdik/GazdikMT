package app.gazdik_app.movie_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import app.gazdik_app.R
import app.gazdik_app.database.Movie
import app.gazdik_app.database.MovieDatabase
import app.gazdik_app.database.getDatabase
import app.gazdik_app.databinding.FragmentMovieBinding
import app.gazdik_app.first_page.FirstFragmentViewModel
import app.gazdik_app.net.MovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Page1Fragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by activityViewModels()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var sendedMovie: MovieData
    private lateinit var movieDB: MovieDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie, container, false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendedMovie = viewModel.movDat[0]
        dataFill()
        println("kontext?: ")
        println(this.context)

        binding.saveButton.setOnClickListener() {
            hrom()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dataFill() {
        binding.movieTitleText.text = "Movie title: " + sendedMovie.title
        binding.movieTitleTypeText.text = "Movie type: " + sendedMovie.titleType
        binding.movieYearText.text = "Year: " + sendedMovie.year.toString()
        binding.movieRuntimeText.text = "Running time (min): " + sendedMovie.runningTime.toString()
    }

    fun hrom(){
        val movie = Movie(
            0,
            sendedMovie.id,sendedMovie.title,sendedMovie.titleType,sendedMovie.year?.toInt(),
            sendedMovie.runningTime?.toInt()
        )
        try {
            movieDB = getDatabase(requireContext())
            GlobalScope.launch(Dispatchers.IO) {
                movieDB.movieDao.insertOne(movie)
                println("Writing to DB should be successfull")

                var listerin = movieDB.movieDao.getAll()
                for (i in 0 until listerin.size){
                    println("movie in DB: " + listerin[i].id +" "+ listerin[i].title)
                }
            }
        } catch (e: Exception) {
            println("ERROR - Exception: \n" + e)
        }
    }

}