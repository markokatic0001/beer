package com.example.beer.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.beer.R
import com.example.beer.databinding.FragmentBeerDetailBinding
import com.example.beer.room.BeerDB

class BeerDetailFragment : Fragment(R.layout.fragment_beer_detail) {

    lateinit var binding: FragmentBeerDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentBeerDetailBinding.bind(view)

        val args: BeerDetailFragmentArgs by navArgs()
        args.beer?.let { setLayout(it) }
    }

    private fun setLayout(beer: BeerDB) {
        binding.name.text = beer.name
        binding.photo.load(beer.imageUrl)
        binding.description.text = beer.description
        binding.abv.text = String.format(getString(R.string.abv), beer.abv.toString())
        binding.ibu.text = String.format(getString(R.string.ibu), beer.ibu.toString())
        binding.ebc.text = String.format(getString(R.string.ebc), beer.ebc.toString())
    }
}