package se.stylianosgakis.marsrealestate.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import se.stylianosgakis.marsrealestate.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModel {
        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        parametersOf(arguments.selectedProperty)
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