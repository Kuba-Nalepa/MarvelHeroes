package com.example.marvelheroes.presentation.fragments.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.databinding.ListItemBinding


class CharactersComicsAdapter(private val characterList: List<ComicBook>) :
    RecyclerView.Adapter<CharactersComicsAdapter.ViewHolder>() {

    private lateinit var context: Context


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = ListItemBinding.inflate(layoutInflater, parent, false)
        context = parent.context
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.comicsTitle.text = characterList[position].title
        val currentImagePath = characterList[position].thumbnail.path
        val currentImageExtension = characterList[position].thumbnail.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(context)
            .load(currentImage)
            .into(holder.comicsImage)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val comicsImage = binding.image
        val comicsTitle = binding.title

    }
}