package com.example.marvelheroes.presentation.fragments.events.eventDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.databinding.CreatorListItemBinding

class CreatorsAdapter(
    private val creators: List<Creator>
): RecyclerView.Adapter<CreatorsAdapter.CreatorsViewHolder>() {

    inner class CreatorsViewHolder(binding: CreatorListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val fullName = binding.fullName
        val role = binding.role

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = CreatorListItemBinding.inflate(layoutInflater, parent, false)
        return  CreatorsViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return creators.size
    }

    override fun onBindViewHolder(holder: CreatorsViewHolder, position: Int) {
        holder.fullName.text = "${creators[position].name}"
        holder.role.text = creators[position].role?.replaceFirstChar {
            it.uppercase()
        }
    }
}