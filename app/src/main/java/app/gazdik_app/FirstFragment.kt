package app.gazdik_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.gazdik_app.databinding.FragmentFirstBinding
import app.gazdik_app.page1.Page1ViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val FFviewModel: FirstFragmentViewModel by lazy {
        ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchView = binding.searchView.query.toString()

        binding.buttonSavedMovies.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonSearch.setOnClickListener {
            searchView = binding.searchView.query.toString()
            FFviewModel.getMovie(searchView);

            //findNavController().navigate(R.id.action_FirstFragment_to_Page1Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun recViewFill() {
        var movielist = binding.returnedMoviesRecyclerView
        for (i in 0 until FFviewModel.movDat.size) {
            println(movielist.get(i))
        }
    }
}