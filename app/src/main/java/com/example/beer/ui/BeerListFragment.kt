package com.example.beer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
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
        observe()
        lifecycleScope.launchWhenStarted {
            viewModel.getBeers()
        }
    }

    private fun setLayout() {
        binding.beerList.layoutManager = LinearLayoutManager(requireContext())
        adapter = BeersAdapter(object: BeersAdapterClickListener{
            override fun onBeerClicked(participant: BeerDB) {

            }
        })
        binding.beerList.adapter = adapter
    }

    private fun observe() {
        viewModel.beersLiveData.observe(viewLifecycleOwner, Observer {
            adapter.beerList = it
        })
    }
}