package com.example.beer.ui.beers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.beer.R
import com.example.beer.room.BeerDB

class BeersAdapter(
        private val listener: BeersAdapterClickListener
) : RecyclerView.Adapter<BeersAdapter.BeerHolder>() {

    var beerList: List<BeerDB> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.beer_list_item, parent, false)
        return BeerHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        holder.bindView(beerList[position])
    }

    override fun getItemCount(): Int = beerList.size

    inner class BeerHolder(val context: Context, itemView: View) :
            RecyclerView.ViewHolder(itemView)  {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val photo = itemView.findViewById<ImageView>(R.id.photo)
        fun bindView(beer: BeerDB) {
            name.text = beer.name
            photo.load(beer.imageUrl)
            itemView.setOnClickListener {
                listener.onBeerClicked(beer)
            }
        }
    }
}