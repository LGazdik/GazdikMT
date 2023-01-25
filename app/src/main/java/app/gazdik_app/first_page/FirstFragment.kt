package app.gazdik_app.first_page

import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.gazdik_app.R
import app.gazdik_app.databinding.FragmentFirstBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.delay


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: FirstFragmentViewModel by activityViewModels()

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

//    val handler = Handler()

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

        val tWGM = Observer<String> { newName ->
            binding.textViewGetedMovies.text = newName
        }
        viewModel.tvgd.observe(viewLifecycleOwner, tWGM)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchViewText: String
        binding.textViewGetedMovies.movementMethod = ScrollingMovementMethod()

        binding.buttonSearch.setOnClickListener {
            searchViewText = binding.searchView.query.toString()
            if (searchViewText != "") {
                viewModel.getMovie(searchViewText)
            } else {
                viewModel.ListClear()
            }
            GlobalScope.launch {
                delay(2000)
                showAll()
            }
                //showAll()

        }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAll() {
        var t = ""

        if (viewModel.movDat.size == 0) {
            binding.textViewGetedMovies.text = ""
        } else {
            for (i in 0 until viewModel.movDat.size) {
                t += viewModel.movDat[i].title.toString() + "\n\n"
                binding.textViewGetedMovies.text = t
                println(viewModel.movDat[i].id + " " + viewModel.movDat[i].title)
            }
        }
        viewModel.tvgd.postValue(t)
    }

}