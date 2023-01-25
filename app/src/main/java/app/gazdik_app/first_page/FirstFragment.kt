package app.gazdik_app.first_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import app.gazdik_app.R
import app.gazdik_app.database.MovieDatabase
import app.gazdik_app.database.getDatabase
import app.gazdik_app.databinding.FragmentFirstBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by activityViewModels()

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
        var searchView: String

        binding.buttonSavedMovies.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonTester.setOnClickListener {
            if (viewModel.movDat.size == 0) {
                println("movDat empty, retuning !!!")
            } else {
                findNavController().navigate(R.id.action_FirstFragment_to_Page1Fragment)
            }
        }

        binding.buttonSearch.setOnClickListener {
            searchView = binding.searchView.query.toString()
            viewModel.getMovie(searchView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}