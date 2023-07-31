package com.example.marvelheroes.presentation.fragments.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.databinding.EventCharacterListItemBinding


class EventsAdapter(private val eventsList: List<Event>): RecyclerView.Adapter<EventsAdapter.RecyclerViewHolder>() {



    inner class RecyclerViewHolder(binding: EventCharacterListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val eventImage = binding.eventImage
        val eventTitle = binding.eventTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = EventCharacterListItemBinding.inflate(layoutInflater, parent, false)
        return  RecyclerViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentImagePath = eventsList[position].thumbnail.path
        val currentImageExtension = eventsList[position].thumbnail.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(holder.itemView)
            .load(currentImage)
            .into(holder.eventImage)

        holder.eventTitle.text = eventsList[position].title
    }

}

