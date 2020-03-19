package se.stylianosgakis.marsrealestate.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import se.stylianosgakis.marsrealestate.R
import se.stylianosgakis.marsrealestate.databinding.FragmentOverviewBinding
import se.stylianosgakis.marsrealestate.model.MarsProperty
import se.stylianosgakis.marsrealestate.util.ApiFilter

class OverviewFragment : Fragment() {
    private val viewModel by viewModel<OverviewViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@OverviewFragment.viewModel
            photosGrid.apply {
                adapter =
                    PhotoGridAdapter(
                        OnClickListener(
                            itemClickedListener
                        )
                    )
            }
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer { marsProperty ->
            marsProperty?.let {
                findNavController().navigate(
                    OverviewFragmentDirections.actionShowDetail(it)
                )
                viewModel.navigateToPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    // On click for the recyclerView
    private val itemClickedListener: (marsProperty: MarsProperty) -> Unit = { marsProperty ->
        viewModel.triggerNavigateToPropertyDetails(marsProperty)
    }

    // Filtering options overflow menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getMarsRealEstateProperties(
            when (item.itemId) {
                R.id.show_rent_menu -> ApiFilter.SHOW_RENT
                R.id.show_buy_menu -> ApiFilter.SHOW_BUY
                else -> ApiFilter.SHOW_ALL
            }
        )
        return true
    }
}
