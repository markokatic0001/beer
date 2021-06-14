package com.example.beer.ui.beers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beer.R
import com.example.beer.databinding.FragmentBeerListBinding
import com.example.beer.room.BeerDB
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerListFragment : Fragment(R.layout.fragment_beer_list) {

    lateinit var binding: FragmentBeerListBinding
    private val viewModel by viewModel<BeerListViewModel>()
    private lateinit var adapter: BeersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBeerListBinding.bind(view)
        setLayout()
        setHasOptionsMenu(true)
        observe()

        lifecycleScope.launchWhenStarted {
            viewModel.prepareList()
        }
    }

    private fun setLayout() {
        binding.beerList.layoutManager = LinearLayoutManager(requireContext())
        adapter = BeersAdapter(object : BeersAdapterClickListener {
            override fun onBeerClicked(beer: BeerDB) {
                requireActivity().findNavController(R.id.navigationFragment).navigate(
                    BeerListFragmentDirections.actionBeerListFragmentToBeerDetailFragment(beer)
                )
            }
        })
        binding.beerList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.abv -> {
                viewModel.getBeersABV()
                true
            }
            R.id.ibu -> {
                viewModel.getBeersIBU()
                true
            }
            R.id.ebc -> {
                viewModel.getBeersEBC()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observe() {
        viewModel.beerListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.beerList = it
        })
    }
}