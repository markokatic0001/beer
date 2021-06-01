package com.example.beer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.beer.R
import com.example.beer.databinding.FragmentBeerListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerListFragment : Fragment(R.layout.fragment_beer_list) {

    lateinit var binding: FragmentBeerListBinding
    private val viewModel by viewModel<BeerListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBeerListBinding.bind(view)

    }
}