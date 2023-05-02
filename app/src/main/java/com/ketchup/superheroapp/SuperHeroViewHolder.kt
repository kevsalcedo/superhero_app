package com.ketchup.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ketchup.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: ItemSuperheroBinding = ItemSuperheroBinding.bind(view)

    fun bind (superheroItemResponse: SuperheroItemResponse,
              onItemSelected:(String) -> Unit){
        binding.tvSuperHeroName.text = superheroItemResponse.superheroName
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }

}