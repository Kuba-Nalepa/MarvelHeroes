package com.example.marvelheroes.presentation.fragments.events

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroes.data.model.Event
import com.example.marvelheroes.databinding.CarouselItemBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class ViewPagerAdapter(listEvents: List<Event>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private lateinit var context: Context

    private val fakeList = listOf(listEvents.last()) + listEvents + listOf(listEvents.first())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = CarouselItemBinding.inflate(layoutInflater, parent, false)
        context = parent.context
        return ViewPagerViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {

        val currentImagePath = fakeList[position].thumbnail.path
        val currentImageExtension = fakeList[position].thumbnail.extension
        val currentImage = "$currentImagePath.$currentImageExtension".replaceFirst("http","https")
        Glide.with(holder.itemView)
            .load(currentImage)
            .fitCenter()
            .into(holder.image)
        Glide.with(holder.itemView)
            .load(currentImage)
            .fitCenter()
            .transform(BlurTransformation())
            .into(holder.backgroundImage)

        holder.title.text = fakeList[position].title

    }

    override fun getItemCount(): Int {
        return fakeList.size
    }

    inner class ViewPagerViewHolder(binding: CarouselItemBinding): RecyclerView.ViewHolder(binding.root) {
        val image = binding.carouselImage
        val title = binding.titleName
        val backgroundImage = binding.backgroundImage

    }
}