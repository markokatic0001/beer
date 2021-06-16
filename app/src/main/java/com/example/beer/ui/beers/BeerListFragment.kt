package com.example.beer.ui.beers

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        binding.beerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    if (adapter.beerList.size > 90) {
                        Toast.makeText(
                            requireContext(),
                            "Sorry in demo you can't get more then 100 beers",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    viewModel.getBeers()
                }
            }
        })
        adapter = BeersAdapter(object : BeersAdapterClickListener {
            override fun onBeerClicked(beer: BeerDB) {
                requireActivity().findNavController(R.id.navigationFragment).navigate(
                    BeerListFragmentDirections.actionBeerListFragmentToBeerDetailFragment(beer)
                )
            }

            override fun onBeerLongClicked(pos: Int) {
                val beer = adapter.beerList[pos]
                beer.id?.let { viewModel.setFavorite(it, beer.favorite?.not(), pos) }
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
            R.id.favorites -> {
                if (viewModel.beerListLiveData.value == null) return true
                requireActivity().findNavController(R.id.navigationFragment).navigate(
                    BeerListFragmentDirections.actionBeerListFragmentToFavoritesFragment(viewModel.beerListLiveData.value!!.filter { it.favorite!! }
                        .toTypedArray())
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observe() {
        viewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
            if (adapter.beerList.isEmpty()) return@Observer
            val beer = adapter.beerList[it]
            beer.favorite?.let { fav -> adapter.updateBeerItem(it, fav.not()) }
        })
        viewModel.beerListLiveData.observe(viewLifecycleOwner, {
            Log.d("TAG", "observe: ${it.size} ${adapter.beerList.size}")
            if (viewModel.filteredBy == 0 && it.isNotEmpty() && adapter.beerList.isNotEmpty() && !it.any { beer -> beer.name == adapter.beerList[0].name }) {
                adapter.beerList.addAll(it)
                adapter.notifyDataSetChanged()
            } else {
                adapter.beerList = it as MutableList<BeerDB>
            }
        })
        viewModel.progressBarLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = it
        })
    }
}