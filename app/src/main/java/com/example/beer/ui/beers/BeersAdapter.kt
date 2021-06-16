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

    var beerList: MutableList<BeerDB> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateBeerItem(pos: Int, favorite: Boolean) {
        beerList[pos].favorite = favorite
        notifyItemChanged(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.beer_list_item, parent, false)
        return BeerHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BeerHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = beerList.size

    inner class BeerHolder(val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val num = itemView.findViewById<TextView>(R.id.num)
        private val photo = itemView.findViewById<ImageView>(R.id.photo)
        private val fav = itemView.findViewById<ImageView>(R.id.favorite)
        fun bindView(pos: Int) {
            val beer = beerList[pos]
            num.text = (pos + 1).toString()
            name.text = beer.name
            photo.load(beer.imageUrl)
            if (beer.favorite == true) {
                fav.load(R.drawable.ic_baseline_favorite_48)
            } else {
                fav.load(R.drawable.ic_baseline_favorite_border_48)
            }
            itemView.setOnClickListener {
                listener.onBeerClicked(beer)
            }
            itemView.setOnLongClickListener { listener.onBeerLongClicked(pos); true }
        }
    }
}