package com.example.beer.ui.favorites

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beer.R
import com.example.beer.databinding.FragmentFavoritesBinding
import com.example.beer.room.BeerDB
import com.example.beer.ui.beers.BeersAdapterClickListener
import com.google.android.flexbox.*

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    lateinit var binding: FragmentFavoritesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFavoritesBinding.bind(view)
        setHasOptionsMenu(true)

        val args: FavoritesFragmentArgs by navArgs()
        args.beers?.let {
            setLayout(it.toMutableList())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            // Redirect "Up/Home" button clicks to our own function
            requireActivity().onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setLayout(favorites: List<BeerDB>) {

        val numberOfColumns = 2
        binding.favoritesGrid.layoutManager = GridLayoutManager(requireContext(), numberOfColumns)

        val layoutManager = FlexboxLayoutManager( requireContext())
        layoutManager.justifyContent = JustifyContent.SPACE_AROUND
        layoutManager.alignItems = AlignItems.CENTER
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.favoritesGrid.layoutManager = layoutManager
        val adapter = MyFavoritesRecyclerViewAdapter(favorites, object : BeersAdapterClickListener {
            override fun onBeerClicked(beer: BeerDB) {

            }

            override fun onBeerLongClicked(pos: Int) {

            }

        })
        binding.favoritesGrid.adapter = adapter
    }
}