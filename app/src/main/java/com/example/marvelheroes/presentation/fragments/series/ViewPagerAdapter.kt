package com.example.marvelheroes.presentation.fragments.series

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.Series
import com.example.marvelheroes.databinding.CarouselItemBinding

class ViewPagerAdapter(private val images: List<Series>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = CarouselItemBinding.inflate(layoutInflater, parent, false)
        return ViewPagerViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {

        val currentImagePath = images[position].thumbnail.path
        val currentImageExtension = images[position].thumbnail.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Log.d("adapter", currentImage)
        Glide.with(holder.itemView)
            .load(currentImage)
            .into(holder.image)


    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewPagerViewHolder(binding: CarouselItemBinding): RecyclerView.ViewHolder(binding.root) {
        val image = binding.carouselImage
    }


}