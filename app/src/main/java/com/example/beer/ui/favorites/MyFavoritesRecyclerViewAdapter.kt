package com.example.beer.ui.favorites

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load

import com.example.beer.databinding.FragmentItemBinding
import com.example.beer.room.BeerDB
import com.example.beer.ui.beers.BeersAdapterClickListener

class MyFavoritesRecyclerViewAdapter(
    private val values: List<BeerDB>,
    private val listener: BeersAdapterClickListener
) : RecyclerView.Adapter<MyFavoritesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.beerImage.load(item.imageUrl)
        holder.beerName.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val beerImage: ImageView = binding.beerImage
        val beerName: TextView = binding.beerName

        override fun toString(): String {
            return super.toString() + " '" + beerName.text + "'"
        }
    }

}