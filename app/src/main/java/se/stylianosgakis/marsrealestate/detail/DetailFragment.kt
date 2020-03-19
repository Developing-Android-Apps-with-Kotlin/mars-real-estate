package se.stylianosgakis.marsrealestate.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import se.stylianosgakis.marsrealestate.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel> {
        val application = requireNotNull(this.activity).application
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        DetailViewModelFactory(arguments.selectedProperty, application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@DetailFragment.viewModel
        }
        return binding.root
    }
}