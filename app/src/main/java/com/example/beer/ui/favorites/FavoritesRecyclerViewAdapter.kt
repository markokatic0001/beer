package com.example.beer.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.beer.R
import com.example.beer.databinding.FavoriteItemBinding
import com.example.beer.room.BeerDB
import com.example.beer.ui.beers.BeersAdapterClickListener

/*
 * Created by Marko 16 June 2021.
 */
class FavoritesRecyclerViewAdapter(
    private val values: List<BeerDB>,
    private val listener: BeersAdapterClickListener
) : RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val beerImage: ImageView = binding.beerImage
        private val beerName: TextView = binding.beerName
        private val favLayout: LinearLayout = binding.favLayout

        fun bindView(pos: Int) {

            val item = values[pos]
            beerImage.load(item.imageUrl) {
                error(R.drawable.ic_baseline_not_interested_48)
            }
            beerName.text = item.name

            favLayout.setOnClickListener {
                listener.onBeerClicked(values[pos])
            }
        }
    }
}