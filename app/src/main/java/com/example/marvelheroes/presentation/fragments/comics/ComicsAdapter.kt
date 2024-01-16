package com.example.marvelheroes.presentation.fragments.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.databinding.EventCharacterListItemBinding


class ComicsAdapter(private var comicsList: List<ComicBook>
): RecyclerView.Adapter<ComicsAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(binding: EventCharacterListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val eventImage = binding.image
        val eventTitle = binding.title
    }

    fun update(list: List<ComicBook>)  {
        comicsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = EventCharacterListItemBinding.inflate(layoutInflater, parent, false)
        return  RecyclerViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentImagePath = comicsList[position].thumbnail.path
        val currentImageExtension = comicsList[position].thumbnail.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(holder.itemView)
            .load(currentImage)
            .into(holder.eventImage)

        holder.eventTitle.text = comicsList[position].title
    }
}