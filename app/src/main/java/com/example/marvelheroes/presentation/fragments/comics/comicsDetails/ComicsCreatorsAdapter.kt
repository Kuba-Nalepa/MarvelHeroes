package com.example.marvelheroes.presentation.fragments.comics.comicsDetails



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelheroes.data.model.Creator
import com.example.marvelheroes.databinding.ComicsCreatorListItemBinding

class ComicsCreatorsAdapter(
    private val creators: List<Creator>
): RecyclerView.Adapter<ComicsCreatorsAdapter.CreatorsViewHolder>() {

    inner class CreatorsViewHolder(binding: ComicsCreatorListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        val fullName = binding.fullName

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = ComicsCreatorListItemBinding.inflate(layoutInflater, parent, false)
        return  CreatorsViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return creators.size
    }

    override fun onBindViewHolder(holder: CreatorsViewHolder, position: Int) {
        holder.fullName.text = "${creators[position].fullName}"
    }
}