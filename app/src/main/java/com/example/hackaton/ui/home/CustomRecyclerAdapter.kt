package com.example.hackaton.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackaton.Place
import com.example.hackaton.R

class CustomRecyclerAdapter(private val places: List<Place>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView? = null
        var textSecondary: TextView? = null
        var textSupporting: TextView? = null

        var photoBackground: ImageView? = null


        init {
            textTitle = itemView.findViewById(R.id.textTitle)
            textSecondary = itemView.findViewById(R.id.textSecondary)
            textSupporting = itemView.findViewById(R.id.textSupport)

            photoBackground = itemView.findViewById(R.id.photoBackground)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textTitle?.text = places[position].name
        holder.textSecondary?.text = places[position].history
        holder.textSupporting?.text = places[position].longitude.toString() + " " + places[position].latitude.toString()

        holder.photoBackground?.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount() = places.size
}