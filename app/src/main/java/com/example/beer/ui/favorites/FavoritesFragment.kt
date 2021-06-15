package com.example.beer.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beer.R
import com.example.beer.databinding.FragmentFavoritesBinding
import com.example.beer.room.BeerDB
import com.example.beer.ui.beers.BeersAdapterClickListener
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModel<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentFavoritesBinding.bind(view)
        setHasOptionsMenu(true)

        val args: FavoritesFragmentArgs by navArgs()
        args.beers?.let {
            setLayout(it.toMutableList())
            Log.d("TAG", "onViewCreated: ARGS")
        } ?: run {
            viewModel.loadFavoritesFromDB()
            Log.d("TAG", "onViewCreated: DB")
        }

        observe()
    }

    private fun observe() {
        viewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer {
            setLayout(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            requireActivity().onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun setLayout(favorites: List<BeerDB>) {

        val numberOfColumns = 2
        binding.favoritesGrid.layoutManager =
            GridLayoutManager(requireContext(), numberOfColumns)

        val layoutManager = FlexboxLayoutManager(requireContext())
        binding.favoritesGrid.layoutManager = layoutManager
        val adapter =
            MyFavoritesRecyclerViewAdapter(favorites, object : BeersAdapterClickListener {
                override fun onBeerClicked(beer: BeerDB) {
                    requireActivity().findNavController(R.id.navigationFragment).navigate(
                        FavoritesFragmentDirections.actionFavoritesFragmentToBeerDetailFragment(
                            beer
                        )
                    )
                }

                override fun onBeerLongClicked(pos: Int) {

                }

            })
        binding.favoritesGrid.adapter = adapter
        Log.d("TAG", "onViewCreated: ${adapter.itemCount}")
    }
}