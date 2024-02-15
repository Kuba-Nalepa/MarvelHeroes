package com.example.marvelheroes.presentation.fragments.characters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.Character
import com.example.marvelheroes.data.model.ComicBook
import com.example.marvelheroes.databinding.CharacterItemBinding

class CharactersViewPagerAdapter(
    private val characterMap: Map<Character,List<ComicBook>>
):RecyclerView.Adapter<CharactersViewPagerAdapter.ViewPagerViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewPagerAdapter.ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = CharacterItemBinding.inflate(layoutInflater, parent, false)
        context = parent.context
        return ViewPagerViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CharactersViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val characterList = characterMap.keys.toList()
        val character = characterList[position]

        holder.characterName.text = character.name
        holder.characterDescription.text = character.description
        val currentImagePath = character.thumbnail?.path
        val currentImageExtension = character.thumbnail?.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(holder.itemView)
            .load(currentImage)
            .fitCenter()
            .into(holder.characterImage)

        holder.comicsRecycler.layoutManager = GridLayoutManager(context,3)
        holder.comicsRecycler.adapter = characterMap[character]
            ?.let { CharactersComicsAdapter(it) }

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return characterMap.size
    }

    inner class ViewPagerViewHolder(binding: CharacterItemBinding): RecyclerView.ViewHolder(binding.root) {
        val characterImage = binding.characterImage
        val characterName = binding.characterName
        val characterDescription = binding.characterDescription
        var comicsRecycler = binding.comicsRecyclerView
    }
}