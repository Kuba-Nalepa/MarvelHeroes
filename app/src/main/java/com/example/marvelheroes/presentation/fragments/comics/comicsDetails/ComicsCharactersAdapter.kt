package com.example.marvelheroes.presentation.fragments.comics.comicsDetails

import com.example.marvelheroes.presentation.fragments.events.eventDetails.OnCharacterClick
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.databinding.FeaturingCharacterListItemBinding

class ComicsCharactersAdapter(
    private val charactersList: List<Character>,
    private val listener: OnCharacterClick
): RecyclerView.Adapter<ComicsCharactersAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(binding: FeaturingCharacterListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val characterImage = binding.image
        val characterName = binding.title
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsCharactersAdapter.RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = FeaturingCharacterListItemBinding.inflate(layoutInflater, parent, false)
        return  RecyclerViewHolder(viewHolder)
    }

    override fun onBindViewHolder(
        holder: ComicsCharactersAdapter.RecyclerViewHolder,
        position: Int
    ) {
        val currentImagePath = charactersList[position].thumbnail?.path
        val currentImageExtension = charactersList[position].thumbnail?.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(holder.itemView)
            .load(currentImage)
            .into(holder.characterImage)

        holder.characterName.text = charactersList[position].name

        holder.itemView.setOnClickListener {
            listener.onCharacterCLick(charactersList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}